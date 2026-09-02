package com.ecart.notification.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationRetryScheduler {

    private final NotificationRepository repository;
    private final NotificationDeliveryService deliveryService;

    @Scheduled(fixedDelay = 30000)
    public void retryFailedNotifications() {
        LocalDateTime now = LocalDateTime.now();
        List<Notification> notifications = repository
                .findTop50ByStatusAndMaxRetryReachedFalseAndNextAttemptAtLessThanEqualOrderByNextAttemptAtAsc(
                        NotificationStatus.EMAIL_FAILED,
                        now
                );

        if (notifications.isEmpty()) {
            return;
        }

        log.info("Retry scheduler picked notifications for replay. count={}, at={}", notifications.size(), now);

        for (Notification notification : notifications) {
            log.info("Retry scheduler attempting notification replay. businessKey={}, retryCount={}, nextAttemptAt={}",
                    notification.getBusinessKey(), notification.getRetryCount(), notification.getNextAttemptAt());
            deliveryService.attemptDelivery(notification, "scheduler");
        }
    }
}
