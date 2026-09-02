package com.ecart.payment.outbox;

public enum OutboxStatus {
    NEW,
    PROCESSING,
    SENT,
    FAILED
}
