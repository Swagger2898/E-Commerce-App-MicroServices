package com.ecart.order.outbox;


import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderOutboxRepository extends JpaRepository<OrderOutboxEvent, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "-2") // Triggers SKIP LOCKED in Postgres
    })
    @Query("SELECT e FROM OrderOutboxEvent e WHERE e.status = :status ORDER BY e.createdAt ASC")
    List<OrderOutboxEvent> findWithSkipLocked(@Param("status") OrderOutboxStatus status, Pageable pageable);


    @Modifying
    @Query("UPDATE OrderOutboxEvent e " +
            "SET e.status = 'NEW' " +
            "WHERE e.status = 'PROCESSING' AND e.updatedAt < :threshold")
    int resetStuckProcessingEvents(@Param("threshold") LocalDateTime threshold);
}