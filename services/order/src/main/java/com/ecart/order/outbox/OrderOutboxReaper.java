package com.ecart.order.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderOutboxReaper {

    private final OrderOutboxRepository orderOutboxRepository;

    // Runs every 5 minutes
    @Scheduled(fixedDelay = 300000)
    @Transactional
    public void recoverStuckEvents() {
        // Any row stuck in PROCESSING for more than 5 minutes means its pod crashed
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(5);
        int recovered = orderOutboxRepository.resetStuckProcessingEvents(threshold);

        if (recovered > 0) {
            log.warn("Reclaimed {} orphaned outbox events stuck in PROCESSING back to NEW", recovered);
        }
    }
}
