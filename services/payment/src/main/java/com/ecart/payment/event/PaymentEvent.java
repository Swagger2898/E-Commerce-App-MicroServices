package com.ecart.payment.event;

import com.ecart.payment.payment.PaymentStatus;

public record PaymentEvent(
        String orderReference,
        PaymentStatus paymentStatus,
        String paymentId
) {
}
