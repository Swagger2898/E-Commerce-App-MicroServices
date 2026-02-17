package com.ecart.product.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message="Product is mandatory")
        Integer productId,
        @Positive(message = "Quantity should be positive")
        double quantity

) {

}
