package com.ecart.order.payment;

import com.ecart.order.config.RazorpayOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    RazorpayOrderResponse requestOrderPayment(@RequestBody PaymentRequest request);
}