package com.ecart.order.orderline;

import com.ecart.order.order.Order;
import org.springframework.stereotype.Service;

@Service

public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {

       return OrderLine.builder()
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .productName(request.productName())
                .productDescription(request.productDescription())
                .purchasedPrice(request.purchasedPrice())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
