package com.ecart.payment.payment;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Payment> findByGatewayOrderId(String gatewayOrderId);

    List<Payment> findTop50ByPaymentStatusAndCreatedAtBefore(PaymentStatus paymentStatus, LocalDateTime createdAt);

}
