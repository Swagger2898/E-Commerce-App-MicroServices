package com.ecart.order.payment;

public record PaymentEvent(
        String orderReference,
        PaymentStatus paymentStatus,
        String paymentId
) {
}
