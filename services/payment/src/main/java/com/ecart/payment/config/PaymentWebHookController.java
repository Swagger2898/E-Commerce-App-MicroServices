package com.ecart.payment.config;

import com.ecart.payment.payment.PaymentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentWebHookController {

    private final PaymentService paymentService; // To update payment status in your DB

    @Value("${razorpay.webhook_secret:${razorpay.key_secret:68cqMF4ORLpMfHL3lxBwTt8n}}")
    private String razorpayWebhookSecret;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleRazorpayWebhook(HttpServletRequest request,
                                                        @RequestHeader(value = "X-Razorpay-Signature", required = false) String razorpaySignature) {

        if (razorpaySignature == null || razorpaySignature.isBlank()) {
            log.warn("Webhook signature missing in request header");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing signature");
        }

        try {
            // Read request body
            String payload = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);

            // Verify webhook signature
            if (!verifySignature(payload, razorpaySignature, razorpayWebhookSecret)) {
                log.warn("Webhook signature verification failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
            }

            log.info("✅ Received Razorpay webhook: {}", payload);

            // Parse JSON payload
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(payload);

            String event = jsonNode.path("event").asText("");

            if ("payment.captured".equals(event)) {
                // Extract payment info
                JsonNode paymentEntity = jsonNode.path("payload").path("payment").path("entity");
                String paymentId = paymentEntity.path("id").asText(null);
                String orderRef = paymentEntity.path("order_id").asText(null);
                Integer amount = paymentEntity.path("amount").asInt(0);

                if (orderRef != null && paymentId != null) {
                    // Call your service to mark payment successful
                    paymentService.handlePaymentCaptured(orderRef, paymentId, amount);
                    log.info("✅ Processed 'payment.captured' for orderRef: {}, paymentId: {}", orderRef, paymentId);
                } else {
                    log.warn("Malformed 'payment.captured' payload: missing order_id or id");
                }
            } else if ("payment.failed".equals(event)) {
                JsonNode paymentEntity = jsonNode.path("payload").path("payment").path("entity");
                String paymentId = paymentEntity.path("id").asText(null);
                String orderRef = paymentEntity.path("order_id").asText(null);

                if (orderRef != null) {
                    paymentService.handlePaymentFailed(orderRef, paymentId);
                    log.info("✅ Processed 'payment.failed' for orderRef: {}, paymentId: {}", orderRef, paymentId);
                } else {
                    log.warn("Malformed 'payment.failed' payload: missing order_id");
                }
            } else {
                log.info("Ignoring unhandled Razorpay webhook event: {}", event);
            }

            return ResponseEntity.ok("Webhook processed");
        } catch (Exception e) {
            log.error("❌ Error handling Razorpay webhook", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Webhook error");
        }
    }

    private boolean verifySignature(String payload, String actualSignature, String secret) {
        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(key);
            byte[] hash = sha256Hmac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String computedSignature = HexFormat.of().formatHex(hash).toLowerCase();


            //The reason is that MessageDigest.isEqual() performs a constant-time comparison, making timing attacks harder.
//========================for safer practises=======================
            return MessageDigest.isEqual(
                    computedSignature.getBytes(StandardCharsets.UTF_8),
                    actualSignature.getBytes(StandardCharsets.UTF_8)
            );
        } catch (Exception e) {
            log.error("❌ Error verifying Razorpay signature", e);
            return false;
        }
    }
}
