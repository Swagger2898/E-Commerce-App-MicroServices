package com.ecart.order.payment;

import com.ecart.order.customer.CustomerClient;
import com.ecart.order.customer.CustomerResponse;
import com.ecart.order.exception.BusinessException;
import com.ecart.order.kafka.OrderConfirmation;
import com.ecart.order.order.Order;
import com.ecart.order.order.OrderRepository;
import com.ecart.order.order.OrderStatus;
import com.ecart.order.orderline.OrderLine;
import com.ecart.order.orderline.OrderLineRepository;
import com.ecart.order.outbox.OrderOutboxEvent;
import com.ecart.order.outbox.OrderOutboxRepository;
import com.ecart.order.outbox.OrderOutboxStatus;
import com.ecart.order.product.ProductClient;
import com.ecart.order.product.ProductResponse;
import com.ecart.order.product.PurchaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentStatusConsumer {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final OrderLineRepository orderLineRepository;
    private final ProductClient productClient;
    private final OrderOutboxRepository orderOutboxRepository;
    private final ObjectMapper objectMapper;

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
            if (order.getStatus() == OrderStatus.CONFIRMED) {
                log.info("Ignoring failed payment event for confirmed order. orderReference={}, currentOrderStatus={}, paymentId={}",
                        order.getReference(), order.getStatus(), event.paymentId());
                return;
            }

            if (order.getStatus() == OrderStatus.PAYMENT_FAILED) {
                log.info("Order {} already marked PAYMENT_FAILED. Skipping duplicate failed payment event.", order.getReference());
                return;
            }

            if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
                log.info("Ignoring failed payment event for non-active order. orderReference={}, currentOrderStatus={}, paymentId={}",
                        order.getReference(), order.getStatus(), event.paymentId());
                return;
            }

            order.setStatus(OrderStatus.PAYMENT_FAILED);
            orderRepository.save(order);
            log.info("Order updated from failed payment event. orderReference={}, orderStatus={}, paymentId={}",
                    order.getReference(), order.getStatus(), event.paymentId());
            return;
        }

        if (event.paymentStatus() == PaymentStatus.SUCCESS) {

            if (order.getStatus() == OrderStatus.PENDING_PAYMENT) {
                log.info("Confirming PENDING_PAYMENT order {}", order.getReference());
            } else {
                log.info("Ignoring success payment event for non-active order. orderReference={}, currentOrderStatus={}, paymentId={}",
                        order.getReference(), order.getStatus(), event.paymentId());
                return;
            }

            order.setStatus(OrderStatus.CONFIRMED);
        } else {
            log.info("Ignoring unsupported payment status {} for order {}", event.paymentStatus(), event.orderReference());
            return;
        }

        OrderConfirmation orderConfirmation = buildOrderConfirmation(order);
        orderRepository.save(order);
        persistOrderConfirmationOutbox(orderConfirmation);
        log.info("Order updated from payment event. orderReference={}, orderStatus={}, paymentId={}",
                order.getReference(), order.getStatus(), event.paymentId());
    }

    private OrderConfirmation buildOrderConfirmation(Order order) {
        CustomerResponse customer = customerClient.findCustomerById(order.getCustomerId())
                .orElseThrow(() -> new BusinessException(
                        "Cannot emit order confirmation:: No customer exists with the provided Id"
                ));

        List<PurchaseResponse> products = orderLineRepository.findAllByOrderId(order.getId()).stream()
                .map(this::toPurchaseResponse)
                .toList();

        return new OrderConfirmation(
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                customer,
                products
        );
    }

    private PurchaseResponse toPurchaseResponse(OrderLine orderLine) {
        ProductResponse product = productClient.findProductById(orderLine.getProductId());
        return new PurchaseResponse(
                product.id(),
                product.name(),
                product.description(),
                product.price(),
                orderLine.getQuantity()
        );
    }

    private void persistOrderConfirmationOutbox(OrderConfirmation orderConfirmation) {
        try {
            orderOutboxRepository.save(
                    OrderOutboxEvent.builder()
                            .orderReference(orderConfirmation.orderReference())
                            .eventType(OrderConfirmation.class.getSimpleName())
                            .payload(objectMapper.writeValueAsString(orderConfirmation))
                            .status(OrderOutboxStatus.NEW)
                            .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize order confirmation for outbox", e);
        }
    }
}
