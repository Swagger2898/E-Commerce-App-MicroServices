package com.ecart.order.order;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest request){
        return Order.builder()
                .reference(request.reference())
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {

    return new OrderResponse(
            order.getId(),
            order.getReference(),
            order.getTotalAmount(),
            order.getPaymentMethod(),
            order.getCustomerId()
        );

    }
}
