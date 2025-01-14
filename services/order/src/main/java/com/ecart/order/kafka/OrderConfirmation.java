package com.ecart.order.kafka;

import com.ecart.order.customer.CustomerResponse;
import com.ecart.order.order.PaymentMethod;
import com.ecart.order.product.PurchaseResponse;
import java.util.List;
import java.math.BigDecimal;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
