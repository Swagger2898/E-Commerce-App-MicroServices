package com.ecart.payment.outbox;

import com.ecart.payment.event.PaymentEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    public void publishNewEvents() {
        List<OutboxEvent> events = outboxRepository.findTop50ByStatusOrderByCreatedAtAsc(OutboxStatus.NEW);

        for (OutboxEvent event : events) {
            try {
                PaymentEvent paymentEvent = objectMapper.readValue(event.getPayload(), PaymentEvent.class);
                kafkaTemplate.send(
                        "payment-status-topic",
                        paymentEvent.orderReference(),
                        paymentEvent
                ).get();
                event.setStatus(OutboxStatus.SENT);
                outboxRepository.save(event);
            } catch (Exception e) {
                log.error("Failed to publish outbox event. outboxEventId={}, eventType={}",
                        event.getId(), event.getEventType(), e);
            }
        }
    }
}
