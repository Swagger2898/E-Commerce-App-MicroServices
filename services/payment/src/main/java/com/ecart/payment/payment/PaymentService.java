package com.ecart.payment.payment;

import com.ecart.payment.event.PaymentEvent;
import com.ecart.payment.outbox.OutboxEvent;
import com.ecart.payment.outbox.OutboxRepository;
import com.ecart.payment.outbox.OutboxStatus;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final RazorpayClient razorpayClient; // injected from RazorpayConfig

    public PaymentController.RazorpayOrderResponse createPayment(PaymentRequest request ) {
        try {
            JSONObject options = new JSONObject();
            options.put("amount", request.amount().multiply(BigDecimal.valueOf(100)).intValue()); // in paise
            options.put("currency", "INR");
            options.put("receipt", request.orderReference());

            Order razorpayOrder = razorpayClient.orders.create(options);
            String razorpayOrderId = razorpayOrder.get("id");

            Payment payment = mapper.toPayment(request);
            payment.setReference(UUID.randomUUID().toString());
            payment.setOrderReference(request.orderReference());
            payment.setGatewayOrderId(razorpayOrderId);

            // Save updated entity
            repository.save(payment);




            // Return DTO
            return new PaymentController.RazorpayOrderResponse(razorpayOrderId);     }
        catch (RazorpayException e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }

    @Transactional
    public void handlePaymentCaptured(String gatewayOrderId, String paymentId, Integer amount) {
        if (gatewayOrderId == null || gatewayOrderId.isBlank()) {
            throw new IllegalArgumentException("Gateway order ID must not be null or blank");
        }

        // 1. Fetch the payment record using orderId (if you stored it)
        Payment payment = repository.findByGatewayOrderId(gatewayOrderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order ID: " + gatewayOrderId));

        if (payment.getOrderReference() == null || payment.getOrderReference().isBlank()) {
            throw new RuntimeException("Order reference is missing for payment with gateway order ID: " + gatewayOrderId);
        }


        // Idempotency check
        if (PaymentStatus.SUCCESS.equals(payment.getPaymentStatus())) {
            log.info("Payment for order {} is already marked SUCCESS. Skipping processing.", gatewayOrderId);
            return;
        }

//        wrong for design
//        // Also check if a paymentId already exists (double safeguard)
//        if (payment.getPaymentId() != null && payment.getPaymentId().equals(paymentId)) {
//            log.info("Duplicate webhook detected for payment ID {}. Skipping.", paymentId);
//            return;
//        }

        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setPaymentId(paymentId);
        payment.setFailedObservationCount(0);
        repository.save(payment);

        PaymentEvent paymentEvent = new PaymentEvent(
                payment.getOrderReference(),
                payment.getPaymentStatus(),
                payment.getPaymentId()
        );

        try {
            outboxRepository.save(
                    OutboxEvent.builder()
                            .eventType(PaymentEvent.class.getSimpleName())
                            .payload(objectMapper.writeValueAsString(paymentEvent))
                            .status(OutboxStatus.NEW)
                            .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize payment event for outbox", e);
        }

        log.info("Handled successful payment for order: {}", gatewayOrderId);
    }

    @Transactional
    public void handlePaymentFailed(String gatewayOrderId, String paymentId) {
        if (gatewayOrderId == null || gatewayOrderId.isBlank()) {
            throw new IllegalArgumentException("Gateway order ID must not be null or blank");
        }

        Payment payment = repository.findByGatewayOrderId(gatewayOrderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order ID: " + gatewayOrderId));

        if (PaymentStatus.SUCCESS.equals(payment.getPaymentStatus()) || PaymentStatus.FAILED.equals(payment.getPaymentStatus())) {
            log.info("Payment for order {} is already finalized with status {}. Skipping failed reconciliation.", gatewayOrderId, payment.getPaymentStatus());
            return;
        }

        payment.setFailedObservationCount(
                payment.getFailedObservationCount() + 1
        );



        if (payment.getFailedObservationCount() < 3) {

            repository.save(payment);

            log.info(
                    "Failed observation count={} for order {}. Keeping payment in PENDING state.",
                    payment.getFailedObservationCount(),
                    gatewayOrderId
            );

            return;
        }

// Third failed observation => terminal FAILED state
        if (paymentId != null && !paymentId.isBlank()) {
            payment.setPaymentId(paymentId);
        }

        payment.setPaymentStatus(PaymentStatus.FAILED);

        repository.save(payment);

        PaymentEvent paymentEvent = new PaymentEvent(
                payment.getOrderReference(),
                payment.getPaymentStatus(),
                payment.getPaymentId()
        );

        try {
            outboxRepository.save(
                    OutboxEvent.builder()
                            .eventType(PaymentEvent.class.getSimpleName())
                            .payload(objectMapper.writeValueAsString(paymentEvent))
                            .status(OutboxStatus.NEW)
                            .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize failed payment event for outbox", e);
        }

        log.info("Handled failed payment for order: {}", gatewayOrderId);
    }

}

