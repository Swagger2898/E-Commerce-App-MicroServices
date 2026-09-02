package com.ecart.order.outbox;

import com.ecart.order.kafka.OrderConfirmation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderOutboxPublisher {

    private static final int BATCH_SIZE = 50;
    private static final int MAX_RETRIES = 5;
    private static final long KAFKA_SEND_TIMEOUT_SECONDS = 10;
    private static final String ORDER_TOPIC = "order-topic";

    private final OrderOutboxDatabaseService orderOutboxDatabaseService;
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    public void publishNewEvents() {
        // --- PHASE 1: Claim batch in short DB transaction (~2ms) ---
        List<OrderOutboxEvent> events = orderOutboxDatabaseService.claimNextBatch(BATCH_SIZE);
        if (events.isEmpty()) {
            return;
        }

        // --- PHASE 2: Async Kafka dispatch (ZERO DB connections held) ---
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (OrderOutboxEvent event : events) {
            OrderConfirmation orderConfirmation;
            try {
                orderConfirmation = objectMapper.readValue(event.getPayload(), OrderConfirmation.class);
            } catch (JsonProcessingException e) {
                markFailed(event, "Malformed payload: " + e.getMessage());
                log.error("Poison order outbox event detected. outboxEventId={}, orderReference={}",
                        event.getId(), event.getOrderReference(), e);
                continue;
            }

            CompletableFuture<?> future = kafkaTemplate.send(
                            ORDER_TOPIC,
                            event.getOrderReference(),
                            orderConfirmation
                    )
                    .orTimeout(KAFKA_SEND_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .whenComplete((sendResult, throwable) -> {
                        if (throwable == null) {
                            event.setStatus(OrderOutboxStatus.SENT);
                            event.setSentAt(LocalDateTime.now());
                        } else {
                            int attempts = event.getRetryCount() + 1;
                            event.setRetryCount(attempts);

                            if (attempts >= MAX_RETRIES) {
                                markFailed(event, throwable.getMessage());
                            } else {
                                // Reset to NEW so subsequent polling cycles can retry
                                event.setStatus(OrderOutboxStatus.NEW);
                            }

                            log.error("Failed to publish order outbox event to Kafka. attempt={}/{}, outboxEventId={}, orderReference={}",
                                    attempts, MAX_RETRIES, event.getId(), event.getOrderReference(), throwable);
                        }
                    });

            futures.add(future);
        }

        if (!futures.isEmpty()) {
            try {
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            } catch (Exception ex) {
                log.debug("One or more Kafka dispatch futures completed exceptionally during order batch join", ex);
            }
        }

        // --- PHASE 3: Persist results in separate short DB transaction (~2ms) ---
        try {
            orderOutboxDatabaseService.persistBatchResults(events);
        } catch (Exception ex) {
            log.error("Failed to batch save order outbox events to DB. Events will be retried on next poll.", ex);
        }
    }

    private void markFailed(OrderOutboxEvent event, String reason) {
        event.setStatus(OrderOutboxStatus.FAILED);
        event.setFailureReason(reason);
    }
}