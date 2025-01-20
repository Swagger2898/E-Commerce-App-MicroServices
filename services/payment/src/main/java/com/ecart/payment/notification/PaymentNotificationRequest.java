package com.ecart.payment.notification;

import java.math.BigDecimal;
import com.ecart.payment.payment.PaymentMethod;

public record PaymentNotificationRequest(

        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstName,

        String customerLastName,

        String customerEmail
) {
}
