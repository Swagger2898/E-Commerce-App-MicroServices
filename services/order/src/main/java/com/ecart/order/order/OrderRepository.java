package com.ecart.order.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByReference(String reference);
    List<Order> findTop50ByStatusAndCreatedAtBefore(OrderStatus status, LocalDateTime createdAt);
}
