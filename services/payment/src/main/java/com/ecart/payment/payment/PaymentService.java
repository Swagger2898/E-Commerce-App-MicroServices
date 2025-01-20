package com.ecart.payment.payment;

import com.ecart.payment.notification.NotificationProducer;
import com.ecart.payment.notification.PaymentNotificationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    public Integer createPayment( PaymentRequest request) {

        var payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotifictaion(
             new PaymentNotificationRequest(   request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));
        return payment.getId();

    }
}
