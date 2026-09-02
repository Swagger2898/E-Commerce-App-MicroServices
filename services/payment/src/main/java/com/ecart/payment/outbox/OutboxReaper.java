package com.ecart.payment.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxReaper {

    private static final int STUCK_THRESHOLD_MINUTES = 5;

    private final OutboxRepository outboxRepository;

    @Scheduled(fixedDelay = 300000) // 5 minutes
    @Transactional
    public void recoverStuckEvents() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(STUCK_THRESHOLD_MINUTES);

        int reclaimedCount = outboxRepository.resetStuckProcessingEvents(
                OutboxStatus.NEW,
                OutboxStatus.PROCESSING,
                threshold
        );

        if (reclaimedCount > 0) {
            log.warn("Reclaimed {} orphaned payment outbox events stuck in PROCESSING back to NEW (threshold={}m)",
                    reclaimedCount, STUCK_THRESHOLD_MINUTES);
        }
    }
}