package kafka;

import com.ecart.notification.email.EmailService;
import jakarta.mail.MessagingException;
import kafka.order.OrderConfirmation;
import kafka.payment.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notification.Notification;
import notification.NotificationRepository;
import notification.NotificationType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        //todo sendEmail
             var customerName = paymentConfirmation.customerFirstname()+ " "+ paymentConfirmation.customerLastname();
             emailService.sentPaymentSuccessEmail(
                     paymentConfirmation.customerEmail(),
                     customerName,
                     paymentConfirmation.amount(),
                     paymentConfirmation.orderReference()
             );
    }


    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //todo sendEmail
        var customerName = orderConfirmation.customer().firstName()+ " "+ orderConfirmation.customer().lastName();
        emailService.sentOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }


}
