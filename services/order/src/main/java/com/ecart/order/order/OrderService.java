package com.ecart.order.order;

import com.ecart.order.config.RazorpayOrderResponse;
import com.ecart.order.customer.CustomerClient;
import com.ecart.order.customer.CustomerResponse;
import com.ecart.order.exception.BusinessException;
import com.ecart.order.kafka.OrderConfirmation;
import com.ecart.order.kafka.OrderProducer;
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

    public Integer createdOrder(OrderRequest request) {

        // 1. Get customer details
        CustomerResponse customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided Id"));

        // 2. Create Razorpay order FIRST
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                null, // orderId not needed yet
                null, // reference will be filled after Razorpay gives it
                customer
        );

        RazorpayOrderResponse razorpayResponse = paymentClient.requestOrderPayment(paymentRequest);
        String razorpayOrderId = razorpayResponse.orderId();

        // 3. Create Order with Razorpay ID as reference
        Order order = mapper.toOrder(request); // this mapper should NOT set reference
        order.setReference(razorpayOrderId);
        order = repository.save(order); // now it's saved with real Razorpay reference

        // 4. Save order lines
        List<PurchaseResponse> purchasedProducts = this.productClient.purchaseProducts(request.products());

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // 5. Send order confirmation
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        order.getReference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

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

