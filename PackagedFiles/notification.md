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
src/main/java/com/ecart/notification/email/EmailService.java
src/main/java/com/ecart/notification/email/EmailTemplates.java
src/main/java/com/ecart/notification/kafka/NotificationConsumer.java
src/main/java/com/ecart/notification/kafka/order/Customer.java
src/main/java/com/ecart/notification/kafka/order/OrderConfirmation.java
src/main/java/com/ecart/notification/kafka/order/Product.java
src/main/java/com/ecart/notification/kafka/payment/PaymentMethod.java
src/main/java/com/ecart/notification/notification/Notification.java
src/main/java/com/ecart/notification/notification/NotificationDeliveryService.java
src/main/java/com/ecart/notification/notification/NotificationRepository.java
src/main/java/com/ecart/notification/notification/NotificationRetryScheduler.java
src/main/java/com/ecart/notification/notification/NotificationStatus.java
src/main/java/com/ecart/notification/notification/NotificationType.java
src/main/java/com/ecart/notification/NotificationApplication.java
src/main/resources/application.properties
src/main/resources/templates/order-confirmation.html
src/main/resources/templates/payment-confirmation.html
src/test/java/com/ecart/notification/NotificationApplicationTests.java
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
	<artifactId>notification</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>notification</name>
	<description>Notification-service</description>
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
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
		</dependency>
		<dependency>
			<groupId>io.zipkin.reporter2</groupId>
			<artifactId>zipkin-reporter-brave</artifactId>
			<version>3.4.0</version>
		</dependency>

		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing-bridge-brave</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
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
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
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
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-mongodb</artifactId>
            <version>4.4.1</version>
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

## File: src/main/java/com/ecart/notification/email/EmailService.java
```java
package com.ecart.notification.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import com.ecart.notification.kafka.order.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

     private final JavaMailSender jms;
     private final SpringTemplateEngine templateEngine;

    public void sentOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    )throws MessagingException {
        MimeMessage mimeMessage = jms.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        mimeMessage.setFrom("swapnilvrinda@gmail.com");
        final String templateName= EmailTemplates.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();

        variables.put("customerName",customerName);
        variables.put("totalAmount",amount);
        variables.put("orderReference",orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            mimeMessageHelper.setTo(destinationEmail);
            jms.send(mimeMessage);
            log.info("Email successfully sent. destinationEmail={}, template={}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.warn("Cannot send email. destinationEmail={}, template={}", destinationEmail, templateName, e);
            throw e;
        }
    }

}
```

## File: src/main/java/com/ecart/notification/email/EmailTemplates.java
```java
package com.ecart.notification.email;

import lombok.Getter;

public enum EmailTemplates {

    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
```

## File: src/main/java/com/ecart/notification/kafka/NotificationConsumer.java
```java
package com.ecart.notification.kafka;

import com.ecart.notification.kafka.order.OrderConfirmation;
import com.ecart.notification.notification.Notification;
import com.ecart.notification.notification.NotificationDeliveryService;
import com.ecart.notification.notification.NotificationRepository;
import com.ecart.notification.notification.NotificationStatus;
import com.ecart.notification.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private static final String ORDER_CONFIRMATION_KEY_PREFIX = "ORDER_CONFIRMATION:";

    private final NotificationRepository repository;
    private final NotificationDeliveryService deliveryService;

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) {
        String businessKey = ORDER_CONFIRMATION_KEY_PREFIX + orderConfirmation.orderReference();
        log.info("Received order confirmation notification event. businessKey={}, orderReference={}",
                businessKey, orderConfirmation.orderReference());

        Notification existing = repository.findByBusinessKey(businessKey).orElse(null);
        if (existing != null) {
            if (existing.getStatus() == NotificationStatus.EMAIL_SENT) {
                log.info("Skipping duplicate delivered notification event. businessKey={}, status={}",
                        businessKey, existing.getStatus());
                return;
            }

            log.info("Skipping duplicate Kafka-triggered retry. businessKey={}, status={}, retryCount={}, nextAttemptAt={}",
                    businessKey, existing.getStatus(), existing.getRetryCount(), existing.getNextAttemptAt());
            return;
        }

        Notification notification = createReceivedNotification(businessKey, orderConfirmation);
        deliveryService.attemptDelivery(notification, "kafka");
    }

    private Notification createReceivedNotification(String businessKey, OrderConfirmation orderConfirmation) {
        Notification notification = Notification.builder()
                .businessKey(businessKey)
                .type(NotificationType.ORDER_CONFIRMATION)
                .status(NotificationStatus.RECEIVED)
                .createdAt(LocalDateTime.now())
                .nextAttemptAt(LocalDateTime.now())
                .retryCount(0)
                .maxRetryReached(false)
                .orderConfirmation(orderConfirmation)
                .build();

        try {
            Notification saved = repository.save(notification);
            log.info("Stored new notification record. businessKey={}, status={}",
                    businessKey, saved.getStatus());
            return saved;
        } catch (DuplicateKeyException e) {
            log.info("Detected concurrent duplicate notification creation. businessKey={}", businessKey);
            return repository.findByBusinessKey(businessKey)
                    .orElseThrow(() -> e);
        }
    }
}
```

## File: src/main/java/com/ecart/notification/kafka/order/Customer.java
```java
package com.ecart.notification.kafka.order;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
```

## File: src/main/java/com/ecart/notification/kafka/order/OrderConfirmation.java
```java
package com.ecart.notification.kafka.order;

import com.ecart.notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        Customer customer,

        List<Product> products
) {
}
```

## File: src/main/java/com/ecart/notification/kafka/order/Product.java
```java
package com.ecart.notification.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
```

## File: src/main/java/com/ecart/notification/kafka/payment/PaymentMethod.java
```java
package com.ecart.notification.kafka.payment;

public enum PaymentMethod {

    PAYPAL,

    CREDIT_CARD,

    VISA,

    MASTER_CARD,

    BITCOIN

}
```

## File: src/main/java/com/ecart/notification/notification/Notification.java
```java
package com.ecart.notification.notification;

import com.ecart.notification.kafka.order.OrderConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;

    @Indexed(unique = true)
    private String businessKey;

    private NotificationType type;

    private NotificationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime lastAttemptAt;

    private LocalDateTime nextAttemptAt;

    private LocalDateTime sentAt;

    private String failureReason;

    private Integer retryCount;

    private Boolean maxRetryReached;

    private OrderConfirmation orderConfirmation;
}
```

## File: src/main/java/com/ecart/notification/notification/NotificationDeliveryService.java
```java
package com.ecart.notification.notification;

import com.ecart.notification.email.EmailService;
import com.ecart.notification.kafka.order.OrderConfirmation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationDeliveryService {

    private static final int MAX_RETRY_COUNT = 5;

    private final NotificationRepository repository;
    private final EmailService emailService;

    public void attemptDelivery(Notification notification, String triggerSource) {
        if (notification.getStatus() == NotificationStatus.EMAIL_SENT) {
            log.info("Skipping notification delivery because it is already sent. businessKey={}, triggerSource={}",
                    notification.getBusinessKey(), triggerSource);
            return;
        }

        if (Boolean.TRUE.equals(notification.getMaxRetryReached())) {
            log.warn("Skipping notification delivery because max retries are exhausted. businessKey={}, retryCount={}, triggerSource={}",
                    notification.getBusinessKey(), notification.getRetryCount(), triggerSource);
            return;
        }

        LocalDateTime attemptTime = LocalDateTime.now();
        notification.setLastAttemptAt(attemptTime);
        repository.save(notification);

        log.info("Starting notification delivery attempt. businessKey={}, triggerSource={}, retryCount={}",
                notification.getBusinessKey(), triggerSource, notification.getRetryCount());

        try {
            sendOrderConfirmationEmail(notification.getOrderConfirmation());
            notification.setStatus(NotificationStatus.EMAIL_SENT);
            notification.setSentAt(LocalDateTime.now());
            notification.setFailureReason(null);
            notification.setNextAttemptAt(null);
            notification.setMaxRetryReached(false);
            repository.save(notification);
            log.info("Notification delivery succeeded. businessKey={}, triggerSource={}, sentAt={}",
                    notification.getBusinessKey(), triggerSource, notification.getSentAt());
        } catch (MessagingException e) {
            int nextRetryCount = notification.getRetryCount() + 1;
            boolean retryExhausted = nextRetryCount >= MAX_RETRY_COUNT;

            notification.setStatus(NotificationStatus.EMAIL_FAILED);
            notification.setRetryCount(nextRetryCount);
            notification.setFailureReason(trimFailureReason(e));
            notification.setMaxRetryReached(retryExhausted);
            notification.setNextAttemptAt(retryExhausted ? null : computeNextAttemptAt(nextRetryCount, attemptTime));
            repository.save(notification);

            if (retryExhausted) {
                log.error("Notification delivery exhausted retries. businessKey={}, retryCount={}, triggerSource={}, reason={}",
                        notification.getBusinessKey(), notification.getRetryCount(), triggerSource, notification.getFailureReason());
            } else {
                log.warn("Notification delivery failed. businessKey={}, retryCount={}, nextAttemptAt={}, triggerSource={}, reason={}",
                        notification.getBusinessKey(), notification.getRetryCount(), notification.getNextAttemptAt(),
                        triggerSource, notification.getFailureReason());
            }
        }
    }

    private void sendOrderConfirmationEmail(OrderConfirmation orderConfirmation) throws MessagingException {
        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sentOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

    private LocalDateTime computeNextAttemptAt(int retryCount, LocalDateTime baseTime) {
        return switch (retryCount) {
            case 1 -> baseTime.plusMinutes(1);
            case 2 -> baseTime.plusMinutes(5);
            case 3 -> baseTime.plusMinutes(15);
            case 4 -> baseTime.plusHours(1);
            default -> null;
        };
    }

    private String trimFailureReason(Exception exception) {
        String message = exception.getMessage();
        if (message == null || message.isBlank()) {
            return exception.getClass().getSimpleName();
        }
        return message.length() > 500 ? message.substring(0, 500) : message;
    }
}
```

## File: src/main/java/com/ecart/notification/notification/NotificationRepository.java
```java
package com.ecart.notification.notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Repository
public interface NotificationRepository  extends MongoRepository<Notification,String> {
    Optional<Notification> findByBusinessKey(String businessKey);
    List<Notification> findTop50ByStatusAndMaxRetryReachedFalseAndNextAttemptAtLessThanEqualOrderByNextAttemptAtAsc(
            NotificationStatus status,
            LocalDateTime nextAttemptAt
    );
}
```

## File: src/main/java/com/ecart/notification/notification/NotificationRetryScheduler.java
```java
package com.ecart.notification.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationRetryScheduler {

    private final NotificationRepository repository;
    private final NotificationDeliveryService deliveryService;

    @Scheduled(fixedDelay = 30000)
    public void retryFailedNotifications() {
        LocalDateTime now = LocalDateTime.now();
        List<Notification> notifications = repository
                .findTop50ByStatusAndMaxRetryReachedFalseAndNextAttemptAtLessThanEqualOrderByNextAttemptAtAsc(
                        NotificationStatus.EMAIL_FAILED,
                        now
                );

        if (notifications.isEmpty()) {
            return;
        }

        log.info("Retry scheduler picked notifications for replay. count={}, at={}", notifications.size(), now);

        for (Notification notification : notifications) {
            log.info("Retry scheduler attempting notification replay. businessKey={}, retryCount={}, nextAttemptAt={}",
                    notification.getBusinessKey(), notification.getRetryCount(), notification.getNextAttemptAt());
            deliveryService.attemptDelivery(notification, "scheduler");
        }
    }
}
```

## File: src/main/java/com/ecart/notification/notification/NotificationStatus.java
```java
package com.ecart.notification.notification;

public enum NotificationStatus {
    RECEIVED,
    EMAIL_SENT,
    EMAIL_FAILED
}
```

## File: src/main/java/com/ecart/notification/notification/NotificationType.java
```java
package com.ecart.notification.notification;

import lombok.Getter;

@Getter
public enum NotificationType {

    ORDER_CONFIRMATION("Order Confirmation");

    private final String subject;

    NotificationType(String subject) {
        this.subject = subject;
    }
}
```

## File: src/main/java/com/ecart/notification/NotificationApplication.java
```java
package com.ecart.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@EnableMongoRepositories(basePackages = "com.ecart.notification.notification")
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

}
```

## File: src/main/resources/application.properties
```
spring.application.name=notification-service
spring.config.import=optional:configserver:http://localhost:8888
```

## File: src/main/resources/templates/order-confirmation.html
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>

    <style>
        body {
          font-family: Arial, sans-serif;
          line-height: 1.6;
          background-color: #f4f4f4;
          margin: 0;
          padding: 0;
        }

        .container {
          max-width: 800px;
          margin: 0 auto;
          padding: 20px;
          background-color: #fff;
          border-radius: 8px;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
          color: #333;
        }

        table {
          width: 100%;
          border-collapse: collapse;
          margin-top: 20px;
        }

        th, td {
          padding: 12px;
          border: 1px solid #ddd;
          text-align: left;
        }

        th {
          background-color: #007BFF;
          color: #fff;
        }

        .footer {
          margin-top: 20px;
          padding-top: 10px;
          border-top: 1px solid #ddd;
          text-align: center;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>Order Details</h1>
    <p>Customer: <span th:text="${customerName}"></span></p>
    <p>Order ID: <span th:text="${orderReference}"></span></p>

    <table>
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="product : ${products}">
            <td th:text="${product.name}"></td>
            <td th:text="${product.quantity}"></td>
            <td th:text="${product.price}"></td>
        </tr>
        </tbody>
    </table>

    <div class="footer">
        <p>Total Amount: $<span th:text="${totalAmount}"></span></p>
        <p>This is an automated message. Please do not reply to this email.</p>
        <p>&copy; 2024 Swapnil</span>. All rights reserved.</p>
    </div>
</div>
</body>

</html>
```

## File: src/main/resources/templates/payment-confirmation.html
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Confirmation</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
        }

        p {
            color: #555;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            color: #fff;
            background-color: #007BFF;
            border-radius: 5px;
        }

        .footer {
            margin-top: 20px;
            padding-top: 10px;
            border-top: 1px solid #ddd;
            text-align: center;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>Payment Confirmation</h1>
    <p>Dear <span th:text="${customerName}"></span>,</p>
    <p>Your payment of $<span th:text="${amount}"></span> has been successfully processed.</p>
    <p>Order reference: <span th:text="${orderReference}"></span></p>
    <p>Thank you for choosing our service. If you have any questions, feel free to contact us.</p>

    <div class="footer">
        <p>This is an automated message. Please do not reply to this email.</p>
        <p>&copy; 2024 Swapnil. All rights reserved.</p>
    </div>
</div>
</body>

</html>
```

## File: src/test/java/com/ecart/notification/NotificationApplicationTests.java
```java
package com.ecart.notification;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotificationApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

## File: src/test/resources/application.properties
```
spring.application.name=notification-service
spring.cloud.config.enabled=false
spring.cloud.config.import-check.enabled=false
eureka.client.enabled=false

spring.data.mongodb.uri=mongodb://localhost:27017/testdb

spring.mail.host=localhost
spring.mail.port=25
spring.mail.username=dummy_user
spring.mail.password=dummy_pass

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.listener.auto-startup=false
spring.kafka.admin.fail-fast=false
spring.kafka.consumer.group-id=notification-service
```
