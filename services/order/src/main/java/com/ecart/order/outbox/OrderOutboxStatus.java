package com.ecart.order.outbox;

public enum OrderOutboxStatus {
    NEW,
    PROCESSING,
    SENT,
    FAILED
}
