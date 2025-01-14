package com.ecart.order.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
         Integer id,
         @NotNull(message = "product is mandatory") Integer orderId,
         Integer productId,
         @Positive(message = "Quantity is mandatory") double quantity) {
}
