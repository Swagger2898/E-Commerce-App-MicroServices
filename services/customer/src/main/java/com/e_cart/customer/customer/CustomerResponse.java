package com.e_cart.customer.customer;

public record CustomerResponse (

    String id,
    String firstname,
    String lastname,
    String email,
    Address address
){
}
