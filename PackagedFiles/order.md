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
src/main/java/com/ecart/order/config/KafkaOrderTopicConfig.java
src/main/java/com/ecart/order/config/RazorpayOrderResponse.java
src/main/java/com/ecart/order/config/RestTemplateConfig.java
src/main/java/com/ecart/order/customer/CustomerClient.java
src/main/java/com/ecart/order/customer/CustomerResponse.java
src/main/java/com/ecart/order/exception/BusinessException.java
src/main/java/com/ecart/order/handler/ErrorResponse.java
src/main/java/com/ecart/order/handler/GlobalExceptionHandler.java
src/main/java/com/ecart/order/kafka/OrderConfirmation.java
src/main/java/com/ecart/order/order/Order.java
src/main/java/com/ecart/order/order/OrderController.java
src/main/java/com/ecart/order/order/OrderExpiryJob.java
src/main/java/com/ecart/order/order/OrderMapper.java
src/main/java/com/ecart/order/order/OrderRepository.java
src/main/java/com/ecart/order/order/OrderRequest.java
src/main/java/com/ecart/order/order/OrderResponse.java
src/main/java/com/ecart/order/order/OrderService.java
src/main/java/com/ecart/order/order/OrderStatus.java
src/main/java/com/ecart/order/order/PaymentMethod.java
src/main/java/com/ecart/order/OrderApplication.java
src/main/java/com/ecart/order/orderline/OrderLine.java
src/main/java/com/ecart/order/orderline/OrderLineController.java
src/main/java/com/ecart/order/orderline/OrderLineMapper.java
src/main/java/com/ecart/order/orderline/OrderLineRepository.java
src/main/java/com/ecart/order/orderline/OrderLineRequest.java
src/main/java/com/ecart/order/orderline/OrderLineResponse.java
src/main/java/com/ecart/order/orderline/OrderLineService.java
src/main/java/com/ecart/order/outbox/OrderOutboxDatabaseService.java
src/main/java/com/ecart/order/outbox/OrderOutboxEvent.java
src/main/java/com/ecart/order/outbox/OrderOutboxPublisher.java
src/main/java/com/ecart/order/outbox/OrderOutboxReaper.java
src/main/java/com/ecart/order/outbox/OrderOutboxRepository.java
src/main/java/com/ecart/order/outbox/OrderOutboxStatus.java
src/main/java/com/ecart/order/payment/PaymentClient.java
src/main/java/com/ecart/order/payment/PaymentEvent.java
src/main/java/com/ecart/order/payment/PaymentRequest.java
src/main/java/com/ecart/order/payment/PaymentStatus.java
src/main/java/com/ecart/order/payment/PaymentStatusConsumer.java
src/main/java/com/ecart/order/product/ProductClient.java
src/main/java/com/ecart/order/product/ProductResponse.java
src/main/java/com/ecart/order/product/PurchaseRequest.java
src/main/java/com/ecart/order/product/PurchaseResponse.java
src/main/java/com/ecart/order/SecurityConfig.java
src/main/resources/application.properties
src/test/java/com/ecart/order/OrderApplicationTests.java
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
	<artifactId>order</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>order</name>
	<description>OrderService</description>
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
		<!-- Spring Boot dependencies -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
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
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>

		<!-- Kafka Clients (optional unless explicitly needed) -->
		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka-clients</artifactId>
		</dependency>

		<!-- PostgreSQL runtime dependency -->
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- Lombok dependency -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<!-- Spring Boot starter test dependency -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
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
		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
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

	<!-- Adding Maven Central explicitly -->
	<repositories>
		<repository>
			<id>central</id>
			<url>https://repo.maven.apache.org/maven2</url>
		</repository>
	</repositories>

</project>
```

## File: src/main/java/com/ecart/order/config/KafkaOrderTopicConfig.java
```java
package com.ecart.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic orderTopic(){
        return TopicBuilder
                .name("order-topic")
                .build();
    }


}
```

## File: src/main/java/com/ecart/order/config/RazorpayOrderResponse.java
```java
package com.ecart.order.config;

public record RazorpayOrderResponse(String orderId) {}
```

## File: src/main/java/com/ecart/order/config/RestTemplateConfig.java
```java
package com.ecart.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
```

## File: src/main/java/com/ecart/order/customer/CustomerClient.java
```java
package com.ecart.order.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
```

## File: src/main/java/com/ecart/order/customer/CustomerResponse.java
```java
package com.ecart.order.customer;

public record CustomerResponse(

        String id,
        String firstname,
        String lastname,
        String email

) {
}
```

## File: src/main/java/com/ecart/order/exception/BusinessException.java
```java
package com.ecart.order.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

        private final String msg;


}
```

## File: src/main/java/com/ecart/order/handler/ErrorResponse.java
```java
package com.ecart.order.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
```

## File: src/main/java/com/ecart/order/handler/GlobalExceptionHandler.java
```java
package com.ecart.order.handler;

import com.ecart.order.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handle(BusinessException exp){
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(exp.getMsg());
}



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exp){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exp.getMessage());
    }





    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp){

    var errors = new HashMap<String,String>();
    exp.getBindingResult().getAllErrors()
            .forEach(error ->{
                var fieldName = ((FieldError)error).getField();
                var errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

}
```

## File: src/main/java/com/ecart/order/kafka/OrderConfirmation.java
```java
package com.ecart.order.kafka;

import com.ecart.order.customer.CustomerResponse;
import com.ecart.order.order.PaymentMethod;
import com.ecart.order.product.PurchaseResponse;
import java.util.List;
import java.math.BigDecimal;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
```

## File: src/main/java/com/ecart/order/order/Order.java
```java
package com.ecart.order.order;

import com.ecart.order.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, updatable = false)
    private String reference;

    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String customerId;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    @CreatedDate
    @Column(updatable=false, nullable=false)
    private LocalDateTime createdAt;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
```

## File: src/main/java/com/ecart/order/order/OrderController.java
```java
package com.ecart.order.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request
    ){
       return ResponseEntity.ok(service.createdOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ){
        return ResponseEntity.ok(service.findById(orderId));
    }
}
```

## File: src/main/java/com/ecart/order/order/OrderExpiryJob.java
```java
package com.ecart.order.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderExpiryJob {

    private final OrderRepository orderRepository;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void expirePendingOrders() {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(10);
        List<Order> orders = orderRepository.findTop50ByStatusInAndCreatedAtBefore(
                List.of(OrderStatus.PENDING_PAYMENT, OrderStatus.PAYMENT_FAILED),
                cutoff
        );

        for (Order order : orders) {
            order.setStatus(OrderStatus.EXPIRED);
            orderRepository.save(order);
            log.info("Expired pending order. orderReference={}, orderId={}", order.getReference(), order.getId());
        }
    }
}
```

## File: src/main/java/com/ecart/order/order/OrderMapper.java
```java
package com.ecart.order.order;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest request){
        return Order.builder()
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .status(OrderStatus.PENDING_PAYMENT)
                .build();
    }

    public OrderResponse fromOrder(Order order) {

    return new OrderResponse(
            order.getId(),
            order.getReference(),
            order.getTotalAmount(),
            order.getPaymentMethod(),
            order.getCustomerId()
        );

    }
}
```

## File: src/main/java/com/ecart/order/order/OrderRepository.java
```java
package com.ecart.order.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByReference(String reference);
    List<Order> findTop50ByStatusAndCreatedAtBefore(OrderStatus status, LocalDateTime createdAt);
    List<Order> findTop50ByStatusInAndCreatedAtBefore(Collection<OrderStatus> statuses, LocalDateTime createdAt);
}
```

## File: src/main/java/com/ecart/order/order/OrderRequest.java
```java
package com.ecart.order.order;

import com.ecart.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        String reference,

        @Positive(message = "Order amount should be positive")
        BigDecimal amount ,

        @NotNull(message="customer method should be present")
        @NotEmpty(message="customer method should be present")
        @NotBlank(message="customer method should be present")
        String customerId,

        @NotNull(message="payment method should be present")
        PaymentMethod paymentMethod,

        @NotEmpty(message="You should purchase at least one product")
        List<PurchaseRequest> products

) {
}
```

## File: src/main/java/com/ecart/order/order/OrderResponse.java
```java
package com.ecart.order.order;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId

) {
}
```

## File: src/main/java/com/ecart/order/order/OrderService.java
```java
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
```

## File: src/main/java/com/ecart/order/order/OrderStatus.java
```java
package com.ecart.order.order;

public enum OrderStatus {
    PENDING_PAYMENT,
    PAYMENT_FAILED,
    CONFIRMED,
    EXPIRED,
    CANCELLED
}
```

## File: src/main/java/com/ecart/order/order/PaymentMethod.java
```java
package com.ecart.order.order;

public enum PaymentMethod {

    PAYPAL,

    CREDIT_CARD,

    VISA,

    MASTER_CARD,

    BITCOIN
}
```

## File: src/main/java/com/ecart/order/OrderApplication.java
```java
package com.ecart.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@EnableScheduling
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
```

## File: src/main/java/com/ecart/order/orderline/OrderLine.java
```java
package com.ecart.order.orderline;


import com.ecart.order.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order-id")
    private Order order;

    private Integer productId;

    private String productName;

    @Column(columnDefinition = "TEXT")
    private String productDescription;

    private BigDecimal purchasedPrice;

    private double quantity;

}
```

## File: src/main/java/com/ecart/order/orderline/OrderLineController.java
```java
package com.ecart.order.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

private final OrderLineService service;

@GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order-id") Integer orderId
){
    return ResponseEntity.ok(service.findAllByOrderId(orderId));
}

}
```

## File: src/main/java/com/ecart/order/orderline/OrderLineMapper.java
```java
package com.ecart.order.orderline;

import com.ecart.order.order.Order;
import org.springframework.stereotype.Service;

@Service

public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {

       return OrderLine.builder()
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .productName(request.productName())
                .productDescription(request.productDescription())
                .purchasedPrice(request.purchasedPrice())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
```

## File: src/main/java/com/ecart/order/orderline/OrderLineRepository.java
```java
package com.ecart.order.orderline;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {



    List<OrderLine> findAllByOrderId(Integer orderId);
}
```

## File: src/main/java/com/ecart/order/orderline/OrderLineRequest.java
```java
package com.ecart.order.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderLineRequest(

         @NotNull(message = "product is mandatory")
         Integer orderId,
         Integer productId,
         String productName,
         String productDescription,
         BigDecimal purchasedPrice,
         @Positive(message = "Quantity is mandatory")
         double quantity) {
}
```

## File: src/main/java/com/ecart/order/orderline/OrderLineResponse.java
```java
package com.ecart.order.orderline;

public record OrderLineResponse(
        Integer id,
         double quantity
) {
}
```

## File: src/main/java/com/ecart/order/orderline/OrderLineService.java
```java
package com.ecart.order.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {

        return repository.findAllByOrderId(orderId)
                .stream().map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
```

## File: src/main/java/com/ecart/order/outbox/OrderOutboxDatabaseService.java
```java
package com.ecart.order.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderOutboxDatabaseService {

    private final OrderOutboxRepository orderOutboxRepository;

    /**
     * TX 1: Lock 50 NEW rows with SKIP LOCKED, flip to PROCESSING,
     * commit, and release the Hikari connection immediately (~2ms).
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<OrderOutboxEvent> claimNextBatch(int batchSize) {
        List<OrderOutboxEvent> events = orderOutboxRepository.findWithSkipLocked(
                OrderOutboxStatus.NEW,
                PageRequest.of(0, batchSize)
        );

        if (events.isEmpty()) {
            return events;
        }

        for (OrderOutboxEvent event : events) {
            event.setStatus(OrderOutboxStatus.PROCESSING);
        }

        return orderOutboxRepository.saveAll(events);
    }

    /**
     * TX 2: Open a fresh connection, batch-update the final states
     * (SENT, FAILED, or back to NEW for retry), and commit (~2ms).
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void persistBatchResults(List<OrderOutboxEvent> events) {
        orderOutboxRepository.saveAll(events);
    }
}
```

## File: src/main/java/com/ecart/order/outbox/OrderOutboxEvent.java
```java
package com.ecart.order.outbox;

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
        name = "order_outbox_event",
        indexes = {
                // Essential for SKIP LOCKED polling throughput
                @Index(name = "idx_outbox_status_created_at", columnList = "status, createdAt")
        }
)
public class OrderOutboxEvent {

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
    private OrderOutboxStatus status;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // 1. Tracks state transitions (NEW -> PROCESSING -> SENT/FAILED)
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

    // 2. Prevents worker-reaper race conditions
    @Version
    private Long version;
}
```

## File: src/main/java/com/ecart/order/outbox/OrderOutboxPublisher.java
```java
package com.ecart.order.outbox;

import com.ecart.order.kafka.OrderConfirmation;
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
public class OrderOutboxPublisher {

    private static final int BATCH_SIZE = 50;
    private static final int MAX_RETRIES = 5;
    private static final long KAFKA_SEND_TIMEOUT_SECONDS = 10;
    private static final String ORDER_TOPIC = "order-topic";

    private final OrderOutboxDatabaseService orderOutboxDatabaseService;
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    public void publishNewEvents() {
        // --- PHASE 1: Claim batch in short DB transaction (~2ms) ---
        List<OrderOutboxEvent> events = orderOutboxDatabaseService.claimNextBatch(BATCH_SIZE);
        if (events.isEmpty()) {
            return;
        }

        // --- PHASE 2: Async Kafka dispatch (ZERO DB connections held) ---
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (OrderOutboxEvent event : events) {
            OrderConfirmation orderConfirmation;
            try {
                orderConfirmation = objectMapper.readValue(event.getPayload(), OrderConfirmation.class);
            } catch (JsonProcessingException e) {
                markFailed(event, "Malformed payload: " + e.getMessage());
                log.error("Poison order outbox event detected. outboxEventId={}, orderReference={}",
                        event.getId(), event.getOrderReference(), e);
                continue;
            }

            CompletableFuture<?> future = kafkaTemplate.send(
                            ORDER_TOPIC,
                            event.getOrderReference(),
                            orderConfirmation
                    )
                    .orTimeout(KAFKA_SEND_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .whenComplete((sendResult, throwable) -> {
                        if (throwable == null) {
                            event.setStatus(OrderOutboxStatus.SENT);
                            event.setSentAt(LocalDateTime.now());
                        } else {
                            int attempts = event.getRetryCount() + 1;
                            event.setRetryCount(attempts);

                            if (attempts >= MAX_RETRIES) {
                                markFailed(event, throwable.getMessage());
                            } else {
                                // Reset to NEW so subsequent polling cycles can retry
                                event.setStatus(OrderOutboxStatus.NEW);
                            }

                            log.error("Failed to publish order outbox event to Kafka. attempt={}/{}, outboxEventId={}, orderReference={}",
                                    attempts, MAX_RETRIES, event.getId(), event.getOrderReference(), throwable);
                        }
                    });

            futures.add(future);
        }

        if (!futures.isEmpty()) {
            try {
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            } catch (Exception ex) {
                log.debug("One or more Kafka dispatch futures completed exceptionally during order batch join", ex);
            }
        }

        // --- PHASE 3: Persist results in separate short DB transaction (~2ms) ---
        try {
            orderOutboxDatabaseService.persistBatchResults(events);
        } catch (Exception ex) {
            log.error("Failed to batch save order outbox events to DB. Events will be retried on next poll.", ex);
        }
    }

    private void markFailed(OrderOutboxEvent event, String reason) {
        event.setStatus(OrderOutboxStatus.FAILED);
        event.setFailureReason(reason);
    }
}
```

## File: src/main/java/com/ecart/order/outbox/OrderOutboxReaper.java
```java
package com.ecart.order.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderOutboxReaper {

    private final OrderOutboxRepository orderOutboxRepository;

    // Runs every 5 minutes
    @Scheduled(fixedDelay = 300000)
    @Transactional
    public void recoverStuckEvents() {
        // Any row stuck in PROCESSING for more than 5 minutes means its pod crashed
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(5);
        int recovered = orderOutboxRepository.resetStuckProcessingEvents(threshold);

        if (recovered > 0) {
            log.warn("Reclaimed {} orphaned outbox events stuck in PROCESSING back to NEW", recovered);
        }
    }
}
```

## File: src/main/java/com/ecart/order/outbox/OrderOutboxRepository.java
```java
package com.ecart.order.outbox;


import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderOutboxRepository extends JpaRepository<OrderOutboxEvent, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "-2") // Triggers SKIP LOCKED in Postgres
    })
    @Query("SELECT e FROM OrderOutboxEvent e WHERE e.status = :status ORDER BY e.createdAt ASC")
    List<OrderOutboxEvent> findWithSkipLocked(@Param("status") OrderOutboxStatus status, Pageable pageable);


    @Modifying
    @Query("UPDATE OrderOutboxEvent e " +
            "SET e.status = 'NEW' " +
            "WHERE e.status = 'PROCESSING' AND e.updatedAt < :threshold")
    int resetStuckProcessingEvents(@Param("threshold") LocalDateTime threshold);
}
```

## File: src/main/java/com/ecart/order/outbox/OrderOutboxStatus.java
```java
package com.ecart.order.outbox;

public enum OrderOutboxStatus {
    NEW,
    PROCESSING,
    SENT,
    FAILED
}
```

## File: src/main/java/com/ecart/order/payment/PaymentClient.java
```java
package com.ecart.order.payment;

import com.ecart.order.config.RazorpayOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    RazorpayOrderResponse requestOrderPayment(@RequestBody PaymentRequest request);
}
```

## File: src/main/java/com/ecart/order/payment/PaymentEvent.java
```java
package com.ecart.order.payment;

public record PaymentEvent(
        String orderReference,
        PaymentStatus paymentStatus,
        String paymentId
) {
}
```

## File: src/main/java/com/ecart/order/payment/PaymentRequest.java
```java
package com.ecart.order.payment;

import com.ecart.order.customer.CustomerResponse;
import com.ecart.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,

        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        CustomerResponse customer
) {
}
```

## File: src/main/java/com/ecart/order/payment/PaymentStatus.java
```java
package com.ecart.order.payment;

public enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED
}
```

## File: src/main/java/com/ecart/order/payment/PaymentStatusConsumer.java
```java
package com.ecart.order.payment;

import com.ecart.order.customer.CustomerClient;
import com.ecart.order.customer.CustomerResponse;
import com.ecart.order.exception.BusinessException;
import com.ecart.order.kafka.OrderConfirmation;
import com.ecart.order.order.Order;
import com.ecart.order.order.OrderRepository;
import com.ecart.order.order.OrderStatus;
import com.ecart.order.orderline.OrderLine;
import com.ecart.order.orderline.OrderLineRepository;
import com.ecart.order.outbox.OrderOutboxEvent;
import com.ecart.order.outbox.OrderOutboxRepository;
import com.ecart.order.outbox.OrderOutboxStatus;
import com.ecart.order.product.ProductClient;
import com.ecart.order.product.ProductResponse;
import com.ecart.order.product.PurchaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentStatusConsumer {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final OrderLineRepository orderLineRepository;
    private final ProductClient productClient;
    private final OrderOutboxRepository orderOutboxRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-status-topic")
    @Transactional
    public void consumePaymentStatus(PaymentEvent event) {


        log.info("Received payment event for orderReference={}, paymentStatus={}, paymentId={}",
                event.orderReference(), event.paymentStatus(), event.paymentId());
        if (event.paymentStatus() == null) {
            log.error(
                    "Invalid payment event: paymentStatus is null. orderReference={}, paymentId={}",
                    event.orderReference(),
                    event.paymentId()
            );
            return;
        }

        Order order = orderRepository.findByReference(event.orderReference())
                .orElseThrow(() -> {
                    log.error("Order not found for payment event. orderReference={}, paymentStatus={}, paymentId={}",
                            event.orderReference(), event.paymentStatus(), event.paymentId());
                    return new RuntimeException("Order not found for reference: " + event.orderReference());
                });
        log.info("Order found for payment event. orderReference={}, currentOrderStatus={}",
                order.getReference(), order.getStatus());

        if (order.getStatus() == OrderStatus.CONFIRMED) {
            log.info("Order {} already confirmed. Skipping payment event.", order.getReference());
            return;
        }

        if (event.paymentStatus() == PaymentStatus.FAILED) {
            if (order.getStatus() == OrderStatus.CONFIRMED) {
                log.info("Ignoring failed payment event for confirmed order. orderReference={}, currentOrderStatus={}, paymentId={}",
                        order.getReference(), order.getStatus(), event.paymentId());
                return;
            }

            if (order.getStatus() == OrderStatus.PAYMENT_FAILED) {
                log.info("Order {} already marked PAYMENT_FAILED. Skipping duplicate failed payment event.", order.getReference());
                return;
            }

            if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
                log.info("Ignoring failed payment event for non-active order. orderReference={}, currentOrderStatus={}, paymentId={}",
                        order.getReference(), order.getStatus(), event.paymentId());
                return;
            }

            order.setStatus(OrderStatus.PAYMENT_FAILED);
            orderRepository.save(order);
            log.info("Order updated from failed payment event. orderReference={}, orderStatus={}, paymentId={}",
                    order.getReference(), order.getStatus(), event.paymentId());
            return;
        }

        if (event.paymentStatus() == PaymentStatus.SUCCESS) {

            if (order.getStatus() == OrderStatus.PENDING_PAYMENT) {
                log.info("Confirming PENDING_PAYMENT order {}", order.getReference());
            } else {
                log.info("Ignoring success payment event for non-active order. orderReference={}, currentOrderStatus={}, paymentId={}",
                        order.getReference(), order.getStatus(), event.paymentId());
                return;
            }

            order.setStatus(OrderStatus.CONFIRMED);
        } else {
            log.info("Ignoring unsupported payment status {} for order {}", event.paymentStatus(), event.orderReference());
            return;
        }

        OrderConfirmation orderConfirmation = buildOrderConfirmation(order);
        orderRepository.save(order);
        persistOrderConfirmationOutbox(orderConfirmation);
        log.info("Order updated from payment event. orderReference={}, orderStatus={}, paymentId={}",
                order.getReference(), order.getStatus(), event.paymentId());
    }

    private OrderConfirmation buildOrderConfirmation(Order order) {
        CustomerResponse customer = customerClient.findCustomerById(order.getCustomerId())
                .orElseThrow(() -> new BusinessException(
                        "Cannot emit order confirmation:: No customer exists with the provided Id"
                ));

        List<PurchaseResponse> products = orderLineRepository.findAllByOrderId(order.getId()).stream()
                .map(this::toPurchaseResponse)
                .toList();

        return new OrderConfirmation(
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                customer,
                products
        );
    }

    private PurchaseResponse toPurchaseResponse(OrderLine orderLine) {
        ProductResponse product = productClient.findProductById(orderLine.getProductId());
        return new PurchaseResponse(
                product.id(),
                product.name(),
                product.description(),
                product.price(),
                orderLine.getQuantity()
        );
    }

    private void persistOrderConfirmationOutbox(OrderConfirmation orderConfirmation) {
        try {
            orderOutboxRepository.save(
                    OrderOutboxEvent.builder()
                            .orderReference(orderConfirmation.orderReference())
                            .eventType(OrderConfirmation.class.getSimpleName())
                            .payload(objectMapper.writeValueAsString(orderConfirmation))
                            .status(OrderOutboxStatus.NEW)
                            .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize order confirmation for outbox", e);
        }
    }
}
```

## File: src/main/java/com/ecart/order/product/ProductClient.java
```java
package com.ecart.order.product;

import com.ecart.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody){

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody,headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>(){};
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl+"/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException(("An error occured while processing the product purchase: "+responseEntity.getStatusCode()));
        }
        return responseEntity.getBody();
    }

    public ProductResponse findProductById(Integer productId) {
        ResponseEntity<ProductResponse> responseEntity = restTemplate.getForEntity(
                productUrl + "/" + productId,
                ProductResponse.class
        );

        if (responseEntity.getStatusCode().isError() || responseEntity.getBody() == null) {
            throw new BusinessException("An error occurred while fetching product details: " + responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }

}
```

## File: src/main/java/com/ecart/order/product/ProductResponse.java
```java
package com.ecart.order.product;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
```

## File: src/main/java/com/ecart/order/product/PurchaseRequest.java
```java
package com.ecart.order.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

    @NotNull(message="product is mandatory")
    Integer productId,
    @Positive(message="Quantity is mandatory")
    double quantity




){
}
```

## File: src/main/java/com/ecart/order/product/PurchaseResponse.java
```java
package com.ecart.order.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Double quantity
) {
}
```

## File: src/main/java/com/ecart/order/SecurityConfig.java
```java
package com.ecart.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
```

## File: src/main/resources/application.properties
```
spring.application.name=order-service
spring.config.import=optional:configserver:http://localhost:8888
```

## File: src/test/java/com/ecart/order/OrderApplicationTests.java
```java
package com.ecart.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

## File: src/test/resources/application.properties
```
spring.application.name=order-service
spring.cloud.config.enabled=false
spring.cloud.config.import-check.enabled=false
eureka.client.enabled=false

spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

application.config.customer-url=http://localhost:8090/api/v1/customer
application.config.product-url=http://localhost:8050/api/v1/products
application.config.payment-url=http://localhost:8060/api/v1/payments

spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/dummy/jwks

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.listener.auto-startup=false
spring.kafka.admin.fail-fast=false
spring.task.scheduling.enabled=false
```
