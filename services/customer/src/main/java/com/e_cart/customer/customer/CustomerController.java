package com.e_cart.customer.customer;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

private final CustomerService service ;

@PostMapping
    public ResponseEntity<String> createCustomer(
        @RequestBody @Valid CustomerRequest request
        ){
    return ResponseEntity.ok(service.createCustomer(request));
}

@PutMapping
    public ResponseEntity<?> updateCustomer(
            @RequestBody @Valid CustomerRequest request
){
    service.updateCustomer(request);
    return ResponseEntity.accepted().build();
}

@GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
    return ResponseEntity.ok(service.findAllCustomers());
}

@GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") String customerId
){
    return ResponseEntity.ok(service.existById(customerId));
}

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(service.findById(customerId));
    }


    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("customer-id") String customerId
    ){
    service.deleteCustomer(customerId);
    return ResponseEntity.accepted().build();
    }
}
