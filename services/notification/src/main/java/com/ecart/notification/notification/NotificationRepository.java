package com.ecart.notification.notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Repository
public interface NotificationRepository  extends MongoRepository<Notification,String> {
    Optional<Notification> findByBusinessKey(String businessKey);
    List<Notification> findTop50ByStatusAndMaxRetryReachedFalseAndNextAttemptAtLessThanEqualOrderByNextAttemptAtAsc(
            NotificationStatus status,
            LocalDateTime nextAttemptAt
    );
}
