package com.ecart.order.order;

import com.ecart.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,

        @Positive(message = "Order amount should be positive")
        BigDecimal amount ,

        @NotNull(message="customer method should be present")
        @NotEmpty(message="customer method should be present")
        @NotBlank(message="customer method should be present")
        String customerId,

        @NotNull(message="payment method should be present")
        PaymentMethod paymentMethod,

        @NotEmpty(message="You should purchase at least one product")
        List<PurchaseRequest> products

) {
}
