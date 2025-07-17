package com.ecart.notification.notification;

import lombok.Getter;

@Getter
public enum NotificationType {

    ORDER_CONFIRMATION("Order Confirmation"),  // ✅ Provide subject
    PAYMENT_CONFIRMATION("Payment Confirmation");

    private final String subject;  // ✅ Add subject field

    NotificationType(String subject) {
        this.subject = subject;
    }
}

