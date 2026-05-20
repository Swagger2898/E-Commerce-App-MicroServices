package com.ecart.order.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderLineRequest(

         @NotNull(message = "product is mandatory")
         Integer orderId,
         Integer productId,
         String productName,
         String productDescription,
         BigDecimal purchasedPrice,
         @Positive(message = "Quantity is mandatory")
         double quantity) {
}
