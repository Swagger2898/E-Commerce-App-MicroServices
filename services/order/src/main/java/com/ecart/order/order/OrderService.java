package com.ecart.order.order;

import com.ecart.order.customer.CustomerClient;
import com.ecart.order.exception.BusinessException;
import com.ecart.order.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    public Integer createdOrder(OrderRequest request){

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order:: No customer exists with the provided Id"));


        //purchase product

    }
}
