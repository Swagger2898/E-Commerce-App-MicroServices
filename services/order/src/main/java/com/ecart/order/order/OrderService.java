package com.ecart.order.order;

import com.ecart.order.customer.CustomerClient;
import com.ecart.order.exception.BusinessException;
import com.ecart.order.kafka.OrderConfirmation;
import com.ecart.order.kafka.OrderProducer;
import com.ecart.order.orderline.OrderLineRequest;
import com.ecart.order.orderline.OrderLineService;
import com.ecart.order.payment.PaymentClient;
import com.ecart.order.payment.PaymentRequest;
import com.ecart.order.product.ProductClient;
import com.ecart.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final OrderMapper mapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createdOrder(OrderRequest request){

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order:: No customer exists with the provided Id"));


        //purchase product
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        var order = this.repository.save(mapper.toOrder(request));
        for(PurchaseRequest purchaseRequest:request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    ));


        }

        //payment
        var paymentRequest= new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);


        //send the order-confirmation ---> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts

                )
        );

        return  order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No order found with provided ID: %d", orderId)));
    }
}

