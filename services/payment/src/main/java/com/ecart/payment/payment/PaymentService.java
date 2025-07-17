package com.ecart.payment.payment;

import com.ecart.payment.notification.NotificationProducer;
import com.ecart.payment.notification.PaymentNotificationRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    private final RazorpayClient razorpayClient; // injected from RazorpayConfig

    public String createPayment(PaymentRequest request) {
        try {
            JSONObject options = new JSONObject();
            options.put("amount", request.amount().multiply(BigDecimal.valueOf(100)).intValue()); // in paise
            options.put("currency", "INR");
            options.put("receipt", request.orderReference());

            Order razorpayOrder = razorpayClient.orders.create(options);

            var payment = repository.save(mapper.toPayment(request));

            notificationProducer.sendNotification(
                    new PaymentNotificationRequest(
                            request.orderReference(),
                            request.amount(),
                            request.paymentMethod(),
                            request.customer().firstName(),
                            request.customer().lastName(),
                            request.customer().email()
                    )
            );

            return razorpayOrder.get("id");
        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }

    public void handlePaymentCaptured(String orderId, String paymentId, Integer amount) {
        // 1. Fetch the payment record using orderId (if you stored it)
        Payment payment = repository.findByOrderReference(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order ID: " + orderId));


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

