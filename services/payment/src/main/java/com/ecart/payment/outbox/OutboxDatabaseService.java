package com.ecart.payment.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxDatabaseService {

    private final OutboxRepository outboxRepository;

    /**
     * TX 1: Claims rows with SKIP LOCKED, marks PROCESSING, commits,
     * and releases the Hikari connection in ~2ms.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<OutboxEvent> claimNextBatch(int batchSize) {
        List<OutboxEvent> events = outboxRepository.findWithSkipLocked(
                OutboxStatus.NEW,
                PageRequest.of(0, batchSize)
        );

        if (events.isEmpty()) {
            return events;
        }

        for (OutboxEvent event : events) {
            event.setStatus(OutboxStatus.PROCESSING);
        }

        return outboxRepository.saveAll(events);
    }

    /**
     * TX 2: Saves final statuses (SENT, FAILED, or back to NEW) in ~2ms.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void persistBatchResults(List<OutboxEvent> events) {
        outboxRepository.saveAll(events);
    }
}