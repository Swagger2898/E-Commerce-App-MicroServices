package com.ecart.payment.payment;

import com.razorpay.RazorpayClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentReconciliationJob {

    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;
    private final RazorpayClient razorpayClient;

    @Scheduled(fixedDelay = 60000)
    public void reconcilePendingPayments() {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(3);
        List<com.ecart.payment.payment.Payment> payments = paymentRepository.findTop50ByPaymentStatusAndCreatedAtBefore(PaymentStatus.PENDING, cutoff);

        log.info("Starting payment reconciliation. pendingCount={}, cutoff={}", payments.size(), cutoff);

        for (com.ecart.payment.payment.Payment payment : payments) {
            try {
                Optional<com.razorpay.Payment> razorpayPayment = resolvePaymentAttempt(payment);

                if (razorpayPayment.isEmpty()) {
                    log.info("No final Razorpay payment attempt found yet. orderReference={}, gatewayOrderId={}",
                            payment.getOrderReference(), payment.getGatewayOrderId());
                    continue;
                }

                com.razorpay.Payment resolvedPayment = razorpayPayment.get();
                String razorpayStatus = resolvedPayment.get("status");
                String resolvedPaymentId = resolvedPayment.get("id");

                if ("captured".equalsIgnoreCase(razorpayStatus)) {
                    log.info("Reconciling payment as SUCCESS. orderReference={}, gatewayOrderId={}, paymentId={}",
                            payment.getOrderReference(), payment.getGatewayOrderId(), resolvedPaymentId);
                    paymentService.handlePaymentCaptured(
                            payment.getGatewayOrderId(),
                            resolvedPaymentId,
                            payment.getAmount().multiply(java.math.BigDecimal.valueOf(100)).intValue()
                    );
                } else if ("failed".equalsIgnoreCase(razorpayStatus)) {
                    log.info("Reconciling payment as FAILED. orderReference={}, gatewayOrderId={}, paymentId={}",
                            payment.getOrderReference(), payment.getGatewayOrderId(), resolvedPaymentId);
                    paymentService.handlePaymentFailed(payment.getGatewayOrderId(), resolvedPaymentId);
                } else if ("created".equalsIgnoreCase(razorpayStatus)
                        || "authorized".equalsIgnoreCase(razorpayStatus)
                        || "attempted".equalsIgnoreCase(razorpayStatus)) {
                    log.info("Ignoring non-final Razorpay payment status. orderReference={}, gatewayOrderId={}, paymentId={}, razorpayStatus={}",
                            payment.getOrderReference(), payment.getGatewayOrderId(), resolvedPaymentId, razorpayStatus);
                } else {
                    log.warn("Unexpected Razorpay payment status during reconciliation. orderReference={}, gatewayOrderId={}, paymentId={}, razorpayStatus={}",
                            payment.getOrderReference(), payment.getGatewayOrderId(), resolvedPaymentId, razorpayStatus);
                }
            } catch (Exception e) {
                log.error("Failed to reconcile payment. orderReference={}, gatewayOrderId={}",
                        payment.getOrderReference(), payment.getGatewayOrderId(), e);
            }
        }
    }

    private Optional<com.razorpay.Payment> resolvePaymentAttempt(com.ecart.payment.payment.Payment payment) throws Exception {
        if (payment.getPaymentId() != null && !payment.getPaymentId().isBlank()) {
            return Optional.of(razorpayClient.payments.fetch(payment.getPaymentId()));
        }

        List<com.razorpay.Payment> attempts = razorpayClient.orders.fetchPayments(payment.getGatewayOrderId());

        return attempts.stream()
                .filter(attempt -> isFinalOrPreferredStatus(attempt.get("status")))
                .max(Comparator.comparingLong(this::extractCreatedAt));
    }

    private boolean isFinalOrPreferredStatus(String status) {
        return "captured".equalsIgnoreCase(status) || "failed".equalsIgnoreCase(status);
    }

    private long extractCreatedAt(com.razorpay.Payment payment) {
        Object createdAt = payment.get("created_at");
        if (createdAt instanceof Number number) {
            return number.longValue();
        }
        return Long.MIN_VALUE;
    }
}
