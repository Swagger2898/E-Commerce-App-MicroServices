This file is a merged representation of a subset of the codebase, containing specifically included files and files not matching ignore patterns, combined into a single document by Repomix.

# File Summary

## Purpose
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  a. A header with the file path (## File: path/to/file)
  b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Only files matching these patterns are included: **/src/**, **/pom.xml
- Files matching these patterns are excluded: **/target/**, **/build/**
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)

# Directory Structure
```
pom.xml
src/main/java/com/ecart/payment/config/KafkaPaymentTopicConfig.java
src/main/java/com/ecart/payment/config/PaymentWebHookController.java
src/main/java/com/ecart/payment/config/practis.java
src/main/java/com/ecart/payment/config/RazorpayConfig.java
src/main/java/com/ecart/payment/event/PaymentEvent.java
src/main/java/com/ecart/payment/outbox/OutboxDatabaseService.java
src/main/java/com/ecart/payment/outbox/OutboxEvent.java
src/main/java/com/ecart/payment/outbox/OutboxPublisher.java
src/main/java/com/ecart/payment/outbox/OutboxReaper.java
src/main/java/com/ecart/payment/outbox/OutboxRepository.java
src/main/java/com/ecart/payment/outbox/OutboxStatus.java
src/main/java/com/ecart/payment/payment/Customer.java
src/main/java/com/ecart/payment/payment/CustomerEntity.java
src/main/java/com/ecart/payment/payment/Payment.java
src/main/java/com/ecart/payment/payment/PaymentController.java
src/main/java/com/ecart/payment/payment/PaymentMapper.java
src/main/java/com/ecart/payment/payment/PaymentMethod.java
src/main/java/com/ecart/payment/payment/PaymentReconciliationJob.java
src/main/java/com/ecart/payment/payment/PaymentRepository.java
src/main/java/com/ecart/payment/payment/PaymentRequest.java
src/main/java/com/ecart/payment/payment/PaymentService.java
src/main/java/com/ecart/payment/payment/PaymentStatus.java
src/main/java/com/ecart/payment/PaymentApplication.java
src/main/resources/application.properties
src/main/resources/razorpay_test.html
src/test/java/com/ecart/payment/PaymentApplicationTests.java
src/test/resources/application.properties
```

# Files

## File: pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.ecart</groupId>
	<artifactId>payment</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>payment</name>
	<description>Payment-Service</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<packaging>jar</packaging>

	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2024.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>


		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.13.0</version> <!-- or latest version -->
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>com.razorpay</groupId>
			<artifactId>razorpay-java</artifactId>
			<version>1.4.1</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>io.zipkin.reporter2</groupId>
			<artifactId>zipkin-reporter-brave</artifactId>
			<version>3.4.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing-bridge-brave</artifactId>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</path>
					</annotationProcessorPaths>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```

## File: src/main/java/com/ecart/payment/config/KafkaPaymentTopicConfig.java
```java
package com.ecart.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {

    @Bean
    public NewTopic paymentStatusTopic(){

    return TopicBuilder.name("payment-status-topic").build();

    }

}
```

## File: src/main/java/com/ecart/payment/config/PaymentWebHookController.java
```java
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
import java.security.MessageDigest;
import java.util.HexFormat;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentWebHookController {

    private final PaymentService paymentService; // To update payment status in your DB
    private final String razorpayWebhookSecret = "68cqMF4ORLpMfHL3lxBwTt8n"; // Set this securely (ideally in application properties)

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
                String orderRef = paymentEntity.get("order_id").asText();
                Integer amount = paymentEntity.get("amount").asInt();

                // Call your service to mark payment successful
                paymentService.handlePaymentCaptured(orderRef, paymentId, amount);

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
```

## File: src/main/java/com/ecart/payment/config/practis.java
```java
package com.ecart.payment.config;

public class practis {



    public static void main(String[] args){

        String s ="100101011010111";
        int k =5;
        System.out.println(maxCount(s,k));

    }

    public static int maxCount(String s , int target) {


        int left =0;
        int maxCount =0;
        int zeroCount =0;

        for(int right=0; right<s.length();right++){

            if(s.charAt(right)=='0'){
                zeroCount++;
            }
            if(zeroCount>target){
                if(s.charAt(left)=='0'){
                  zeroCount--;
                }
                left++;
            }

            maxCount= Math.max(maxCount, right-left+1);
        }
   return maxCount;


    }
}
```

## File: src/main/java/com/ecart/payment/config/RazorpayConfig.java
```java
package com.ecart.payment.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class RazorpayConfig {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(keyId, keySecret);
    }
}
```

## File: src/main/java/com/ecart/payment/event/PaymentEvent.java
```java
package com.ecart.payment.event;

import com.ecart.payment.payment.PaymentStatus;

public record PaymentEvent(
        String orderReference,
        PaymentStatus paymentStatus,
        String paymentId
) {
}
```

## File: src/main/java/com/ecart/payment/outbox/OutboxDatabaseService.java
```java
package com.ecart.payment.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxDatabaseService {

    private final OutboxRepository outboxRepository;

    /**
     * TX 1: Claims rows with SKIP LOCKED, marks PROCESSING, commits,
     * and releases the Hikari connection in ~2ms.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<OutboxEvent> claimNextBatch(int batchSize) {
        List<OutboxEvent> events = outboxRepository.findWithSkipLocked(
                OutboxStatus.NEW,
                PageRequest.of(0, batchSize)
        );

        if (events.isEmpty()) {
            return events;
        }

        for (OutboxEvent event : events) {
            event.setStatus(OutboxStatus.PROCESSING);
        }

        return outboxRepository.saveAll(events);
    }

    /**
     * TX 2: Saves final statuses (SENT, FAILED, or back to NEW) in ~2ms.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void persistBatchResults(List<OutboxEvent> events) {
        outboxRepository.saveAll(events);
    }
}
```

## File: src/main/java/com/ecart/payment/outbox/OutboxEvent.java
```java
package com.ecart.payment.outbox;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "payment_outbox_event",
        indexes = {
                // Prevents full table scans on high-throughput polling
                @Index(name = "idx_payment_outbox_status_created_at", columnList = "status, createdAt")
        }
)
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String orderReference;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OutboxStatus status;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // 1. Updated automatically on every transition (e.g., NEW -> PROCESSING)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime sentAt;

    @Builder.Default
    @Column(nullable = false)
    private int retryCount = 0;

    @Column(columnDefinition = "TEXT")
    private String failureReason;

    // 2. Guards against silent overwrite races between publisher and reaper
    @Version
    private Long version;
}
```

## File: src/main/java/com/ecart/payment/outbox/OutboxPublisher.java
```java
package com.ecart.payment.outbox;

import com.ecart.payment.event.PaymentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxPublisher {

    private static final int BATCH_SIZE = 50;
    private static final int MAX_RETRIES = 5;
    private static final long KAFKA_SEND_TIMEOUT_SECONDS = 10;
    private static final String PAYMENT_STATUS_TOPIC = "payment-status-topic";

    private final OutboxDatabaseService outboxDatabaseService;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    public void publishNewEvents() {
        // --- PHASE 1: Fast claim in short DB transaction (~2ms) ---
        List<OutboxEvent> events = outboxDatabaseService.claimNextBatch(BATCH_SIZE);
        if (events.isEmpty()) {
            return;
        }

        // --- PHASE 2: Kafka network I/O (ZERO DB connections held) ---
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (OutboxEvent event : events) {
            PaymentEvent paymentEvent;
            try {
                paymentEvent = objectMapper.readValue(event.getPayload(), PaymentEvent.class);
            } catch (JsonProcessingException e) {
                markFailed(event, "Malformed payload: " + e.getMessage());
                log.error("Poison outbox event detected. outboxEventId={}", event.getId(), e);
                continue;
            }

            CompletableFuture<?> future = kafkaTemplate.send(
                            PAYMENT_STATUS_TOPIC,
                            paymentEvent.orderReference(),
                            paymentEvent
                    )
                    .orTimeout(KAFKA_SEND_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .whenComplete((sendResult, throwable) -> {
                        if (throwable == null) {
                            event.setStatus(OutboxStatus.SENT);
                            event.setSentAt(LocalDateTime.now());
                        } else {
                            int attempts = event.getRetryCount() + 1;
                            event.setRetryCount(attempts);

                            if (attempts >= MAX_RETRIES) {
                                markFailed(event, throwable.getMessage());
                            } else {
                                // Reset back to NEW so a subsequent cycle picks it up for retry
                                event.setStatus(OutboxStatus.NEW);
                            }

                            log.error("Failed to publish outbox event to Kafka. attempt={}/{}, outboxEventId={}",
                                    attempts, MAX_RETRIES, event.getId(), throwable);
                        }
                    });

            futures.add(future);
        }

        if (!futures.isEmpty()) {
            try {
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            } catch (Exception ex) {
                log.debug("One or more Kafka dispatches completed exceptionally during batch join", ex);
            }
        }

        // --- PHASE 3: Fast update in separate short DB transaction (~2ms) ---
        try {
            outboxDatabaseService.persistBatchResults(events);
        } catch (Exception ex) {
            log.error("Failed to persist final batch outbox states to DB.", ex);
        }
    }

    private void markFailed(OutboxEvent event, String reason) {
        event.setStatus(OutboxStatus.FAILED);
        event.setFailureReason(reason);
    }
}
```

## File: src/main/java/com/ecart/payment/outbox/OutboxReaper.java
```java
package com.ecart.payment.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxReaper {

    private static final int STUCK_THRESHOLD_MINUTES = 5;

    private final OutboxRepository outboxRepository;

    @Scheduled(fixedDelay = 300000) // 5 minutes
    @Transactional
    public void recoverStuckEvents() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(STUCK_THRESHOLD_MINUTES);

        int reclaimedCount = outboxRepository.resetStuckProcessingEvents(
                OutboxStatus.NEW,
                OutboxStatus.PROCESSING,
                threshold
        );

        if (reclaimedCount > 0) {
            log.warn("Reclaimed {} orphaned payment outbox events stuck in PROCESSING back to NEW (threshold={}m)",
                    reclaimedCount, STUCK_THRESHOLD_MINUTES);
        }
    }
}
```

## File: src/main/java/com/ecart/payment/outbox/OutboxRepository.java
```java
package com.ecart.payment.outbox;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxEvent, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "-2")
    })
    @Query("SELECT e FROM OutboxEvent e WHERE e.status = :status ORDER BY e.createdAt ASC")
    List<OutboxEvent> findWithSkipLocked(@Param("status") OutboxStatus status, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE OutboxEvent e " +
            "SET e.status = :targetStatus " +
            "WHERE e.status = :stuckStatus AND e.updatedAt < :threshold")
    int resetStuckProcessingEvents(
            @Param("targetStatus") OutboxStatus targetStatus,
            @Param("stuckStatus") OutboxStatus stuckStatus,
            @Param("threshold") LocalDateTime threshold
    );
}
```

## File: src/main/java/com/ecart/payment/outbox/OutboxStatus.java
```java
package com.ecart.payment.outbox;

public enum OutboxStatus {
    NEW,
    PROCESSING,
    SENT,
    FAILED
}
```

## File: src/main/java/com/ecart/payment/payment/Customer.java
```java
package com.ecart.payment.payment;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message="FirstName is required")
        String firstName,
        @NotNull(message="LastName is required")
        String lastName,
        @NotNull(message="Email is required")
        @Email(message="The email isn't valid")
        String email
) {
}
```

## File: src/main/java/com/ecart/payment/payment/CustomerEntity.java
```java
package com.ecart.payment.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "customer_id")
    private String id;

    @Column(name = "customer_first_name")
    private String firstName;

    @Column(name = "customer_last_name")
    private String lastName;

    @Column(name = "customer_email")
    private String email;


}
```

## File: src/main/java/com/ecart/payment/payment/Payment.java
```java
package com.ecart.payment.payment;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(
        AuditingEntityListener.class
)
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String reference;
    private String orderReference; // ✅ Needed to look up payments via orderRef
    private String gatewayOrderId;
    private String paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @CreatedDate
    @Column(updatable=false, nullable=false)
    private LocalDateTime createdAt;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @Column(nullable = false)
    private Integer failedObservationCount = 0;

    //persisting data
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "customer_id")),
            @AttributeOverride(name = "firstName", column = @Column(name = "customer_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "customer_last_name")),
            @AttributeOverride(name = "email", column = @Column(name = "customer_email"))
    })
    private CustomerEntity customer;
}
```

## File: src/main/java/com/ecart/payment/payment/PaymentController.java
```java
package com.ecart.payment.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    public record RazorpayOrderResponse(String orderId) {
    }

    @PostMapping
    public ResponseEntity<RazorpayOrderResponse> createPayment(@RequestBody @Valid PaymentRequest request) {
        RazorpayOrderResponse response = service.createPayment(request);
        return ResponseEntity.ok(response);
    }


}
```

## File: src/main/java/com/ecart/payment/payment/PaymentMapper.java
```java
package com.ecart.payment.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .orderReference(request.orderReference()) // ✅ needed for webhook lookup
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .paymentStatus(PaymentStatus.PENDING) // optional default
                .customer(toCustomerEntity(request.customer())) // ✅ persist customer
                .build();
    }

    // From DTO to Entity
    public CustomerEntity toCustomerEntity(Customer customer) {
        if (customer == null) return null;
        return CustomerEntity.builder()
                .id(customer.id())
                .firstName(customer.firstName())
                .lastName(customer.lastName())
                .email(customer.email())
                .build();
    }


}
```

## File: src/main/java/com/ecart/payment/payment/PaymentMethod.java
```java
package com.ecart.payment.payment;

public enum PaymentMethod {
    PAYPAL,

    CREDIT_CARD,

    VISA,

    MASTER_CARD,

    BITCOIN
}
```

## File: src/main/java/com/ecart/payment/payment/PaymentReconciliationJob.java
```java
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

    private Optional<com.razorpay.Payment> resolvePaymentAttempt(
            com.ecart.payment.payment.Payment payment
    ) throws Exception {

        if (payment.getPaymentId() != null
                && !payment.getPaymentId().isBlank()) {
            return Optional.of(
                    razorpayClient.payments.fetch(
                            payment.getPaymentId()
                    )
            );
        }

        List<com.razorpay.Payment> attempts =
                razorpayClient.orders.fetchPayments(
                        payment.getGatewayOrderId()
                );

        // First preference: any CAPTURED payment
        Optional<com.razorpay.Payment> capturedPayment =
                attempts.stream()
                        .filter(attempt ->
                                "captured".equalsIgnoreCase(
                                        attempt.get("status")
                                )
                        )
                        .max(
                                Comparator.comparingLong(
                                        this::extractCreatedAt
                                )
                        );

        if (capturedPayment.isPresent()) {
            return capturedPayment;
        }

        // Second preference: latest FAILED payment
        return attempts.stream()
                .filter(attempt ->
                        "failed".equalsIgnoreCase(
                                attempt.get("status")
                        )
                )
                .max(
                        Comparator.comparingLong(
                                this::extractCreatedAt
                        )
                );
    }



    private long extractCreatedAt(com.razorpay.Payment payment) {
        Object createdAt = payment.get("created_at");
        if (createdAt instanceof Number number) {
            return number.longValue();
        }
        return Long.MIN_VALUE;
    }
}
```

## File: src/main/java/com/ecart/payment/payment/PaymentRepository.java
```java
package com.ecart.payment.payment;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Payment> findByGatewayOrderId(String gatewayOrderId);

    List<Payment> findTop50ByPaymentStatusAndCreatedAtBefore(PaymentStatus paymentStatus, LocalDateTime createdAt);

}
```

## File: src/main/java/com/ecart/payment/payment/PaymentRequest.java
```java
package com.ecart.payment.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        Customer customer
) {
}
```

## File: src/main/java/com/ecart/payment/payment/PaymentService.java
```java
package com.ecart.payment.payment;

import com.ecart.payment.event.PaymentEvent;
import com.ecart.payment.outbox.OutboxEvent;
import com.ecart.payment.outbox.OutboxRepository;
import com.ecart.payment.outbox.OutboxStatus;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final RazorpayClient razorpayClient; // injected from RazorpayConfig

    public PaymentController.RazorpayOrderResponse createPayment(PaymentRequest request ) {
        try {
            JSONObject options = new JSONObject();
            options.put("amount", request.amount().multiply(BigDecimal.valueOf(100)).intValue()); // in paise
            options.put("currency", "INR");
            options.put("receipt", request.orderReference());

            Order razorpayOrder = razorpayClient.orders.create(options);
            String razorpayOrderId = razorpayOrder.get("id");

            Payment payment = mapper.toPayment(request);
            payment.setReference(UUID.randomUUID().toString());
            payment.setOrderReference(request.orderReference());
            payment.setGatewayOrderId(razorpayOrderId);

            // Save updated entity
            repository.save(payment);




            // Return DTO
            return new PaymentController.RazorpayOrderResponse(razorpayOrderId);     }
        catch (RazorpayException e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }

    @Transactional
    public void handlePaymentCaptured(String gatewayOrderId, String paymentId, Integer amount) {
        if (gatewayOrderId == null || gatewayOrderId.isBlank()) {
            throw new IllegalArgumentException("Gateway order ID must not be null or blank");
        }

        // 1. Fetch the payment record using orderId (if you stored it)
        Payment payment = repository.findByGatewayOrderId(gatewayOrderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order ID: " + gatewayOrderId));

        if (payment.getOrderReference() == null || payment.getOrderReference().isBlank()) {
            throw new RuntimeException("Order reference is missing for payment with gateway order ID: " + gatewayOrderId);
        }


        // Idempotency check
        if (PaymentStatus.SUCCESS.equals(payment.getPaymentStatus())) {
            log.info("Payment for order {} is already marked SUCCESS. Skipping processing.", gatewayOrderId);
            return;
        }

//        wrong for design
//        // Also check if a paymentId already exists (double safeguard)
//        if (payment.getPaymentId() != null && payment.getPaymentId().equals(paymentId)) {
//            log.info("Duplicate webhook detected for payment ID {}. Skipping.", paymentId);
//            return;
//        }

        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setPaymentId(paymentId);
        payment.setFailedObservationCount(0);
        repository.save(payment);

        PaymentEvent paymentEvent = new PaymentEvent(
                payment.getOrderReference(),
                payment.getPaymentStatus(),
                payment.getPaymentId()
        );

        try {
            outboxRepository.save(
                    OutboxEvent.builder()
                            .eventType(PaymentEvent.class.getSimpleName())
                            .payload(objectMapper.writeValueAsString(paymentEvent))
                            .status(OutboxStatus.NEW)
                            .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize payment event for outbox", e);
        }

        log.info("Handled successful payment for order: {}", gatewayOrderId);
    }

    @Transactional
    public void handlePaymentFailed(String gatewayOrderId, String paymentId) {
        if (gatewayOrderId == null || gatewayOrderId.isBlank()) {
            throw new IllegalArgumentException("Gateway order ID must not be null or blank");
        }

        Payment payment = repository.findByGatewayOrderId(gatewayOrderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order ID: " + gatewayOrderId));

        if (PaymentStatus.SUCCESS.equals(payment.getPaymentStatus()) || PaymentStatus.FAILED.equals(payment.getPaymentStatus())) {
            log.info("Payment for order {} is already finalized with status {}. Skipping failed reconciliation.", gatewayOrderId, payment.getPaymentStatus());
            return;
        }

        payment.setFailedObservationCount(
                payment.getFailedObservationCount() + 1
        );



        if (payment.getFailedObservationCount() < 3) {

            repository.save(payment);

            log.info(
                    "Failed observation count={} for order {}. Keeping payment in PENDING state.",
                    payment.getFailedObservationCount(),
                    gatewayOrderId
            );

            return;
        }

// Third failed observation => terminal FAILED state
        if (paymentId != null && !paymentId.isBlank()) {
            payment.setPaymentId(paymentId);
        }

        payment.setPaymentStatus(PaymentStatus.FAILED);

        repository.save(payment);

        PaymentEvent paymentEvent = new PaymentEvent(
                payment.getOrderReference(),
                payment.getPaymentStatus(),
                payment.getPaymentId()
        );

        try {
            outboxRepository.save(
                    OutboxEvent.builder()
                            .eventType(PaymentEvent.class.getSimpleName())
                            .payload(objectMapper.writeValueAsString(paymentEvent))
                            .status(OutboxStatus.NEW)
                            .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize failed payment event for outbox", e);
        }

        log.info("Handled failed payment for order: {}", gatewayOrderId);
    }

}
```

## File: src/main/java/com/ecart/payment/payment/PaymentStatus.java
```java
package com.ecart.payment.payment;

public enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED
}
```

## File: src/main/java/com/ecart/payment/PaymentApplication.java
```java
package com.ecart.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}
```

## File: src/main/resources/application.properties
```
spring.application.name=payment-service
spring.config.import=optional:configserver:http://localhost:8888
```

## File: src/main/resources/razorpay_test.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Razorpay Test</title>
</head>
<body>

<h2>Pay Now</h2>
<button id="pay-btn">Pay</button>

<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>
    document.getElementById("pay-btn").onclick = function (e) {
        var options = {
            "key": "rzp_test_5pZoJqQBlKYB8U",
            "amount": "15000",
            "currency": "INR",
            "name": "Your Store",
            "description": "Test Payment",
            "order_id": "order_RGda6loWvRfcT2",
            "handler": function (response){
                alert("✅ Payment Success! Payment ID: " + response.razorpay_payment_id);
                console.log(response);
            },
            "prefill": {
                "name": "Swapnil",
                "email": "swapnil@example.com"
            },
            "theme": {
                "color": "#3399cc"
            }
        };

        var rzp = new Razorpay(options);
        rzp.open();
        e.preventDefault();
    }
</script>

</body>
</html>
```

## File: src/test/java/com/ecart/payment/PaymentApplicationTests.java
```java
package com.ecart.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

## File: src/test/resources/application.properties
```
spring.application.name=payment-service
spring.cloud.config.enabled=false
spring.cloud.config.import-check.enabled=false
eureka.client.enabled=false

spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

razorpay.key_id=dummy_test_key
razorpay.key_secret=dummy_test_secret
application.config.product-url=http://localhost:8222/api/v1/products

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.listener.auto-startup=false
spring.kafka.admin.fail-fast=false
spring.task.scheduling.enabled=false
```
