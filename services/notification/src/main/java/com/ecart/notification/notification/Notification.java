package com.ecart.notification.notification;

import com.ecart.notification.kafka.order.OrderConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;

    @Indexed(unique = true)
    private String businessKey;

    private NotificationType type;

    private NotificationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime lastAttemptAt;

    private LocalDateTime nextAttemptAt;

    private LocalDateTime sentAt;

    private String failureReason;

    private Integer retryCount;

    private Boolean maxRetryReached;

    private OrderConfirmation orderConfirmation;
}
