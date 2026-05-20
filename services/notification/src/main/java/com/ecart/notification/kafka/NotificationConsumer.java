package com.ecart.notification.kafka;

import com.ecart.notification.kafka.order.OrderConfirmation;
import com.ecart.notification.notification.Notification;
import com.ecart.notification.notification.NotificationDeliveryService;
import com.ecart.notification.notification.NotificationRepository;
import com.ecart.notification.notification.NotificationStatus;
import com.ecart.notification.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private static final String ORDER_CONFIRMATION_KEY_PREFIX = "ORDER_CONFIRMATION:";

    private final NotificationRepository repository;
    private final NotificationDeliveryService deliveryService;

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) {
        String businessKey = ORDER_CONFIRMATION_KEY_PREFIX + orderConfirmation.orderReference();
        log.info("Received order confirmation notification event. businessKey={}, orderReference={}",
                businessKey, orderConfirmation.orderReference());

        Notification existing = repository.findByBusinessKey(businessKey).orElse(null);
        if (existing != null) {
            if (existing.getStatus() == NotificationStatus.EMAIL_SENT) {
                log.info("Skipping duplicate delivered notification event. businessKey={}, status={}",
                        businessKey, existing.getStatus());
                return;
            }

            log.info("Skipping duplicate Kafka-triggered retry. businessKey={}, status={}, retryCount={}, nextAttemptAt={}",
                    businessKey, existing.getStatus(), existing.getRetryCount(), existing.getNextAttemptAt());
            return;
        }

        Notification notification = createReceivedNotification(businessKey, orderConfirmation);
        deliveryService.attemptDelivery(notification, "kafka");
    }

    private Notification createReceivedNotification(String businessKey, OrderConfirmation orderConfirmation) {
        Notification notification = Notification.builder()
                .businessKey(businessKey)
                .type(NotificationType.ORDER_CONFIRMATION)
                .status(NotificationStatus.RECEIVED)
                .createdAt(LocalDateTime.now())
                .nextAttemptAt(LocalDateTime.now())
                .retryCount(0)
                .maxRetryReached(false)
                .orderConfirmation(orderConfirmation)
                .build();

        try {
            Notification saved = repository.save(notification);
            log.info("Stored new notification record. businessKey={}, status={}",
                    businessKey, saved.getStatus());
            return saved;
        } catch (DuplicateKeyException e) {
            log.info("Detected concurrent duplicate notification creation. businessKey={}", businessKey);
            return repository.findByBusinessKey(businessKey)
                    .orElseThrow(() -> e);
        }
    }
}
