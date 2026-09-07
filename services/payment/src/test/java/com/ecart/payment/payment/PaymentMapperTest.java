package com.ecart.payment.payment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentMapperTest {

    private final PaymentMapper mapper = new PaymentMapper();

    @Test
    void paymentBuilderDefaultsFailedObservationCountToZero() {
        Payment payment = Payment.builder().build();
        assertThat(payment.getFailedObservationCount()).isEqualTo(0);
    }

    @Test
    void paymentMapperSetsFailedObservationCountToZero() {
        PaymentRequest request = new PaymentRequest(
                null,
                BigDecimal.valueOf(100),
                PaymentMethod.CREDIT_CARD,
                1,
                "ref-123",
                new Customer("cust-1", "John", "Doe", "john@example.com")
        );

        Payment payment = mapper.toPayment(request);
        assertThat(payment.getFailedObservationCount()).isEqualTo(0);
    }

    @Test
    void prePersistSetsDefaultWhenNull() {
        Payment payment = new Payment();
        payment.setFailedObservationCount(null);
        payment.prePersist();
        assertThat(payment.getFailedObservationCount()).isEqualTo(0);
    }
}
