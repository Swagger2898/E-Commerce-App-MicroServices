package com.ecart.order.payment;

import com.ecart.order.customer.CustomerResponse;
import com.ecart.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRepuest(

        BigDecimal amount,

        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        CustomerResponse customer
) {
}
