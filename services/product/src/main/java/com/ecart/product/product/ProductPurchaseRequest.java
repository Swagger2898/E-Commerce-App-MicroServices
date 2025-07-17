package com.ecart.product.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message="Product is mandatory")
        Integer productId,
        @NotNull(message ="Quality is mandatory")
        double quantity

) {

}
