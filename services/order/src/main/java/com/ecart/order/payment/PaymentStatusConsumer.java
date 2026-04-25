package com.ecart.order.payment;

import com.ecart.order.order.Order;
import com.ecart.order.order.OrderRepository;
import com.ecart.order.order.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentStatusConsumer {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "payment-status-topic")
    @Transactional
    public void consumePaymentStatus(PaymentEvent event) {


        log.info("Received payment event for orderReference={}, paymentStatus={}, paymentId={}",
                event.orderReference(), event.paymentStatus(), event.paymentId());
        if (event.paymentStatus() == null) {
            log.error(
                    "Invalid payment event: paymentStatus is null. orderReference={}, paymentId={}",
                    event.orderReference(),
                    event.paymentId()
            );
            return;
        }

        Order order = orderRepository.findByReference(event.orderReference())
                .orElseThrow(() -> {
                    log.error("Order not found for payment event. orderReference={}, paymentStatus={}, paymentId={}",
                            event.orderReference(), event.paymentStatus(), event.paymentId());
                    return new RuntimeException("Order not found for reference: " + event.orderReference());
                });
        log.info("Order found for payment event. orderReference={}, currentOrderStatus={}",
                order.getReference(), order.getStatus());

        if (order.getStatus() == OrderStatus.CONFIRMED) {
            log.info("Order {} already confirmed. Skipping payment event.", order.getReference());
            return;
        }

        if (event.paymentStatus() == PaymentStatus.FAILED) {
            log.info("Ignoring failed payment event. orderReference={}, currentOrderStatus={}, paymentId={}",
                    order.getReference(), order.getStatus(), event.paymentId());
            return;
        }

        if (event.paymentStatus() == PaymentStatus.SUCCESS) {

            if (order.getStatus() == OrderStatus.PENDING) {
                log.info("Confirming PENDING order {}", order.getReference());
            } else if (order.getStatus() == OrderStatus.CANCELLED) {
                log.warn("Late payment success. Re-confirming CANCELLED order {}", order.getReference());
            }

            order.setStatus(OrderStatus.CONFIRMED);
        } else {
            log.info("Ignoring unsupported payment status {} for order {}", event.paymentStatus(), event.orderReference());
            return;
        }

        orderRepository.save(order);
        log.info("Order updated from payment event. orderReference={}, orderStatus={}, paymentId={}",
                order.getReference(), order.getStatus(), event.paymentId());
    }
}
