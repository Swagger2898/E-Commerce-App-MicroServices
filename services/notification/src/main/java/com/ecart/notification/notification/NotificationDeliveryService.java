package com.ecart.notification.notification;

import com.ecart.notification.email.EmailService;
import com.ecart.notification.kafka.order.OrderConfirmation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationDeliveryService {

    private static final int MAX_RETRY_COUNT = 5;

    private final NotificationRepository repository;
    private final EmailService emailService;

    public void attemptDelivery(Notification notification, String triggerSource) {
        if (notification.getStatus() == NotificationStatus.EMAIL_SENT) {
            log.info("Skipping notification delivery because it is already sent. businessKey={}, triggerSource={}",
                    notification.getBusinessKey(), triggerSource);
            return;
        }

        if (Boolean.TRUE.equals(notification.getMaxRetryReached())) {
            log.warn("Skipping notification delivery because max retries are exhausted. businessKey={}, retryCount={}, triggerSource={}",
                    notification.getBusinessKey(), notification.getRetryCount(), triggerSource);
            return;
        }

        LocalDateTime attemptTime = LocalDateTime.now();
        notification.setLastAttemptAt(attemptTime);
        repository.save(notification);

        log.info("Starting notification delivery attempt. businessKey={}, triggerSource={}, retryCount={}",
                notification.getBusinessKey(), triggerSource, notification.getRetryCount());

        try {
            sendOrderConfirmationEmail(notification.getOrderConfirmation());
            notification.setStatus(NotificationStatus.EMAIL_SENT);
            notification.setSentAt(LocalDateTime.now());
            notification.setFailureReason(null);
            notification.setNextAttemptAt(null);
            notification.setMaxRetryReached(false);
            repository.save(notification);
            log.info("Notification delivery succeeded. businessKey={}, triggerSource={}, sentAt={}",
                    notification.getBusinessKey(), triggerSource, notification.getSentAt());
        } catch (MessagingException e) {
            int nextRetryCount = notification.getRetryCount() + 1;
            boolean retryExhausted = nextRetryCount >= MAX_RETRY_COUNT;

            notification.setStatus(NotificationStatus.EMAIL_FAILED);
            notification.setRetryCount(nextRetryCount);
            notification.setFailureReason(trimFailureReason(e));
            notification.setMaxRetryReached(retryExhausted);
            notification.setNextAttemptAt(retryExhausted ? null : computeNextAttemptAt(nextRetryCount, attemptTime));
            repository.save(notification);

            if (retryExhausted) {
                log.error("Notification delivery exhausted retries. businessKey={}, retryCount={}, triggerSource={}, reason={}",
                        notification.getBusinessKey(), notification.getRetryCount(), triggerSource, notification.getFailureReason());
            } else {
                log.warn("Notification delivery failed. businessKey={}, retryCount={}, nextAttemptAt={}, triggerSource={}, reason={}",
                        notification.getBusinessKey(), notification.getRetryCount(), notification.getNextAttemptAt(),
                        triggerSource, notification.getFailureReason());
            }
        }
    }

    private void sendOrderConfirmationEmail(OrderConfirmation orderConfirmation) throws MessagingException {
        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sentOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

    private LocalDateTime computeNextAttemptAt(int retryCount, LocalDateTime baseTime) {
        return switch (retryCount) {
            case 1 -> baseTime.plusMinutes(1);
            case 2 -> baseTime.plusMinutes(5);
            case 3 -> baseTime.plusMinutes(15);
            case 4 -> baseTime.plusHours(1);
            default -> null;
        };
    }

    private String trimFailureReason(Exception exception) {
        String message = exception.getMessage();
        if (message == null || message.isBlank()) {
            return exception.getClass().getSimpleName();
        }
        return message.length() > 500 ? message.substring(0, 500) : message;
    }
}
