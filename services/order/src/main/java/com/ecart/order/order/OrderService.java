package com.ecart.order.order;

import com.ecart.order.customer.CustomerClient;
import com.ecart.order.customer.CustomerResponse;
import com.ecart.order.exception.BusinessException;
import com.ecart.order.orderline.OrderLineRequest;
import com.ecart.order.orderline.OrderLineService;
import com.ecart.order.payment.PaymentClient;
import com.ecart.order.payment.PaymentRequest;
import com.ecart.order.product.ProductClient;
import com.ecart.order.product.PurchaseRequest;
import com.ecart.order.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final OrderMapper mapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;

    public Integer createdOrder(OrderRequest request) {
        String orderReference = UUID.randomUUID().toString();

        // 1. Get customer details
        CustomerResponse customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided Id"));

        // 2. Create Razorpay order FIRST
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                null, // orderId not needed yet
                orderReference,
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        // 3. Create Order with a business UUID reference
        Order order = mapper.toOrder(request); // this mapper should NOT set reference
        order.setReference(orderReference);
        order = repository.save(order);

        // 4. Save order lines
        List<PurchaseResponse> purchasedProducts = this.productClient.purchaseProducts(request.products());

        for (PurchaseResponse purchasedProduct : purchasedProducts) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(),
                            purchasedProduct.productId(),
                            purchasedProduct.name(),
                            purchasedProduct.description(),
                            purchasedProduct.price(),
                            purchasedProduct.quantity()
                    )
            );
        }

        return order.getId();
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

