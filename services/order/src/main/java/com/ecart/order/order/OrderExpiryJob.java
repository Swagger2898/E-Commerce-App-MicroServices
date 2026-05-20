package com.ecart.order.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderExpiryJob {

    private final OrderRepository orderRepository;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void expirePendingOrders() {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(10);
        List<Order> orders = orderRepository.findTop50ByStatusInAndCreatedAtBefore(
                List.of(OrderStatus.PENDING_PAYMENT, OrderStatus.PAYMENT_FAILED),
                cutoff
        );

        for (Order order : orders) {
            order.setStatus(OrderStatus.EXPIRED);
            orderRepository.save(order);
            log.info("Expired pending order. orderReference={}, orderId={}", order.getReference(), order.getId());
        }
    }
}
