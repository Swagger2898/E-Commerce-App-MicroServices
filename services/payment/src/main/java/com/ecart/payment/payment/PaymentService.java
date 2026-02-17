package com.ecart.payment.payment;

import com.ecart.payment.notification.NotificationProducer;
import com.ecart.payment.notification.PaymentNotificationRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.ecart.payment.payment.PaymentMethod.PAYPAL;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    private final RazorpayClient razorpayClient; // injected from RazorpayConfig

    public void sendMessage(){
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        "orderRef",
                        new BigDecimal(100.0),
                        PAYPAL,
                        "Swapnil",
                        "Bhurkunde",
                        "swapnil@gmail.com"
                )
        );
         System.out.println("Successfull");
                 }

    public PaymentController.RazorpayOrderResponse createPayment(PaymentRequest request ) {
        try {
            JSONObject options = new JSONObject();
            options.put("amount", request.amount().multiply(BigDecimal.valueOf(100)).intValue()); // in paise
            options.put("currency", "INR");
            options.put("receipt", request.orderReference());

            Order razorpayOrder = razorpayClient.orders.create(options);
            String razorpayOrderId = razorpayOrder.get("id");

            Payment payment = mapper.toPayment(request);

            payment.setOrderReference(razorpayOrderId);

            // Save updated entity
            repository.save(payment);




            // Return DTO
            return new PaymentController.RazorpayOrderResponse(razorpayOrderId);     }
        catch (RazorpayException e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }

    @Transactional
    public void handlePaymentCaptured(String orderId, String paymentId, Integer amount) {
        // 1. Fetch the payment record using orderId (if you stored it)
        Payment payment = repository.findByOrderReference(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order ID: " + orderId));


        // Idempotency check
        if (PaymentStatus.SUCCESS.equals(payment.getPaymentStatus())) {
            log.info("Payment for order {} is already marked SUCCESS. Skipping processing.", orderId);
            return;
        }

        // Also check if a paymentId already exists (double safeguard)
        if (payment.getPaymentId() != null && payment.getPaymentId().equals(paymentId)) {
            log.info("Duplicate webhook detected for payment ID {}. Skipping.", paymentId);
            return;
        }

        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setPaymentId(paymentId);
        repository.save(payment);

        CustomerEntity customer = payment.getCustomer();

        // 3. Send Kafka notification
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        payment.getOrderReference(),
                        payment.getAmount(),
                        payment.getPaymentMethod(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail()
                )
        );

        log.info("Handled successful payment for order: {}", orderId);
    }

}

