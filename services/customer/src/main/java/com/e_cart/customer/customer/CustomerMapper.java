package com.e_cart.customer.customer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(@Valid CustomerRequest request) {
  if(request==null){
      return null ;
  }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .email(request.email())
                .lastname(request.lastname())
                .address(request.address())
                .build();

    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );

    }
}
