package com.ecart.payment.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .orderReference(request.orderReference()) // ✅ needed for webhook lookup
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .paymentStatus(PaymentStatus.PENDING) // optional default
                .customer(toCustomerEntity(request.customer())) // ✅ persist customer
                .build();
    }

    // From DTO to Entity
    public CustomerEntity toCustomerEntity(Customer customer) {
        if (customer == null) return null;
        return CustomerEntity.builder()
                .id(customer.id())
                .firstName(customer.firstName())
                .lastName(customer.lastName())
                .email(customer.email())
                .build();
    }


}
