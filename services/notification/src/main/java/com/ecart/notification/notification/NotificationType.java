package com.ecart.notification.notification;

import lombok.Getter;

@Getter
public enum NotificationType {

    ORDER_CONFIRMATION("Order Confirmation");

    private final String subject;

    NotificationType(String subject) {
        this.subject = subject;
    }
}
