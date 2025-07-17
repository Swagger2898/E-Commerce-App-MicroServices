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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentWebHookController {

    private final PaymentService paymentService; // To update payment status in your DB
    private final String razorpayWebhookSecret = "your_webhook_secret_here"; // Set this securely (ideally in application properties)

    @PostMapping("/webhook")
    public ResponseEntity<String> handleRazorpayWebhook(HttpServletRequest request,
                                                        @RequestHeader("X-Razorpay-Signature") String razorpaySignature) {
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

            String event = jsonNode.get("event").asText();

            if ("payment.captured".equals(event)) {
                // Extract payment info
                JsonNode paymentEntity = jsonNode.get("payload").get("payment").get("entity");
                String paymentId = paymentEntity.get("id").asText();
                String orderId = paymentEntity.get("order_id").asText();
                Integer amount = paymentEntity.get("amount").asInt();

                // Call your service to mark payment successful
                paymentService.handlePaymentCaptured(orderId, paymentId, amount);

                log.info("✅ Processed 'payment.captured' for paymentId: {}", paymentId);
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

            return computedSignature.equals(actualSignature);
        } catch (Exception e) {
            log.error("❌ Error verifying Razorpay signature", e);
            return false;
        }
    }
}
