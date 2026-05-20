package com.ecart.payment.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    public record RazorpayOrderResponse(String orderId) {
    }

    @PostMapping
    public ResponseEntity<RazorpayOrderResponse> createPayment(@RequestBody @Valid PaymentRequest request) {
        RazorpayOrderResponse response = service.createPayment(request);
        return ResponseEntity.ok(response);
    }


}
