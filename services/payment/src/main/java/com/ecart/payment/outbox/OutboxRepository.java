package com.ecart.payment.outbox;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxEvent, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "-2")
    })
    @Query("SELECT e FROM OutboxEvent e WHERE e.status = :status ORDER BY e.createdAt ASC")
    List<OutboxEvent> findWithSkipLocked(@Param("status") OutboxStatus status, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE OutboxEvent e " +
            "SET e.status = :targetStatus " +
            "WHERE e.status = :stuckStatus AND e.updatedAt < :threshold")
    int resetStuckProcessingEvents(
            @Param("targetStatus") OutboxStatus targetStatus,
            @Param("stuckStatus") OutboxStatus stuckStatus,
            @Param("threshold") LocalDateTime threshold
    );
}