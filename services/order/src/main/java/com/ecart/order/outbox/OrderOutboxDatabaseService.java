package com.ecart.order.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderOutboxDatabaseService {

    private final OrderOutboxRepository orderOutboxRepository;

    /**
     * TX 1: Lock 50 NEW rows with SKIP LOCKED, flip to PROCESSING,
     * commit, and release the Hikari connection immediately (~2ms).
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<OrderOutboxEvent> claimNextBatch(int batchSize) {
        List<OrderOutboxEvent> events = orderOutboxRepository.findWithSkipLocked(
                OrderOutboxStatus.NEW,
                PageRequest.of(0, batchSize)
        );

        if (events.isEmpty()) {
            return events;
        }

        for (OrderOutboxEvent event : events) {
            event.setStatus(OrderOutboxStatus.PROCESSING);
        }

        return orderOutboxRepository.saveAll(events);
    }

    /**
     * TX 2: Open a fresh connection, batch-update the final states
     * (SENT, FAILED, or back to NEW for retry), and commit (~2ms).
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void persistBatchResults(List<OrderOutboxEvent> events) {
        orderOutboxRepository.saveAll(events);
    }
}