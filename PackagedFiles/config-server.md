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
src/main/java/com/e_cart/config_server/ConfigServerApplication.java
src/main/resources/application.properties
src/main/resources/configurations/application.properties
src/main/resources/configurations/customer-service-k8s.properties
src/main/resources/configurations/customer-service.properties
src/main/resources/configurations/discovery-service-k8s.properties
src/main/resources/configurations/discovery-service.properties
src/main/resources/configurations/gateway-service-k8s.properties
src/main/resources/configurations/gateway-service.properties
src/main/resources/configurations/notification-service-k8s.properties
src/main/resources/configurations/notification-service.properties
src/main/resources/configurations/order-service-k8s.properties
src/main/resources/configurations/order-service.properties
src/main/resources/configurations/payment-service-k8s.properties
src/main/resources/configurations/payment-service.properties
src/main/resources/configurations/product-service-k8s.properties
src/main/resources/configurations/product-service.properties
src/test/java/com/e_cart/config_server/ConfigServerApplicationTests.java
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
	<groupId>com.e-cart</groupId>
	<artifactId>config-server</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>config-server</name>
	<description>config-server</description>
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
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
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
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

## File: src/main/java/com/e_cart/config_server/ConfigServerApplication.java
```java
package com.e_cart.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
```

## File: src/main/resources/application.properties
```
server.port=8888
spring.profiles.active=native
spring.cloud.config.server.native.search-locations=classpath:/configurations
spring.application.name=config-server
```

## File: src/main/resources/configurations/application.properties
```
eureka.instance.prefer-ip-address=true

management.tracing.sampling.probability=1.0

management.zipkin.tracing.endpoint=http://zipkin:9411/api/v2/spans
```

## File: src/main/resources/configurations/customer-service-k8s.properties
```
spring.data.mongodb.host=mongodb
eureka.client.service-url.defaultZone=http://discovery:8761/eureka
spring.zipkin.base-url=http://zipkin:9411
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://keycloak:8080/realms/ecart-realm
```

## File: src/main/resources/configurations/customer-service.properties
```
server.port=8090

spring.data.mongodb.username=username
spring.data.mongodb.password=password
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=customer
spring.data.mongodb.authentication-database=admin

spring.zipkin.base-url=http://localhost:9422

# Keycloak Configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/ecart-realm

management.endpoint.health.probes.enabled=true
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
```

## File: src/main/resources/configurations/discovery-service-k8s.properties
```
eureka.instance.hostname=discovery
eureka.client.service-url.defaultZone=http://discovery:8761/eureka/
```

## File: src/main/resources/configurations/discovery-service.properties
```
# Eureka server configuration
spring.application.name=discovery-service
eureka.instance.hostname=localhost
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

# Eureka client configuration (disabling registration and fetching registry)
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

# Server configuration
server.port=8761

management.endpoint.health.probes.enabled=true
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
```

## File: src/main/resources/configurations/gateway-service-k8s.properties
```
eureka.client.service-url.defaultZone=http://discovery:8761/eureka
spring.security.oauth2.client.provider.keycloak.issuer-uri=http://keycloak:8080/realms/ecommerce-realm
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://keycloak:8080/realms/ecommerce-realm
```

## File: src/main/resources/configurations/gateway-service.properties
```
spring.application.name=gateway-service
server.port=8222

spring.cloud.gateway.discovery.locator.enabled=true

# Route for Customer Service
spring.cloud.gateway.routes[0].id=customer-service
spring.cloud.gateway.routes[0].uri=lb:http://CUSTOMER-SERVICE
spring.cloud.gateway.routes[0].predicates=Path=/api/v1/customers/**
spring.cloud.gateway.routes[0].filters=TokenRelay

# Route for Order Service
spring.cloud.gateway.routes[1].id=order-service
spring.cloud.gateway.routes[1].uri=lb:http://ORDER-SERVICE
spring.cloud.gateway.routes[1].predicates=Path=/api/v1/orders/**
spring.cloud.gateway.routes[1].filters=TokenRelay

# Route for Order Lines Service
spring.cloud.gateway.routes[2].id=order-lines-service
spring.cloud.gateway.routes[2].uri=lb:http://ORDER-SERVICE
spring.cloud.gateway.routes[2].predicates=Path=/api/v1/order-lines/**
spring.cloud.gateway.routes[2].filters=TokenRelay

# Route for Product Service
spring.cloud.gateway.routes[3].id=product-service
spring.cloud.gateway.routes[3].uri=lb:http://PRODUCT-SERVICE
spring.cloud.gateway.routes[3].predicates=Path=/api/v1/products/**
spring.cloud.gateway.routes[3].filters=TokenRelay

# Route for Payment Service
spring.cloud.gateway.routes[4].id=payment-service
spring.cloud.gateway.routes[4].uri=lb:http://PAYMENT-SERVICE
spring.cloud.gateway.routes[4].predicates=Path=/api/v1/payment/**
spring.cloud.gateway.routes[4].filters=TokenRelay

# Keycloak Configuration
spring.security.oauth2.client.provider.keycloak.issuer-uri=http://localhost:8080/realms/ecommerce-realm
spring.security.oauth2.client.registration.keycloak.provider=keycloak
spring.security.oauth2.client.registration.keycloak.client-id=gateway-client
spring.security.oauth2.client.registration.keycloak.client-secret=FoYLZXVD17RG62u4LkSv0B69abs2vP5h
spring.security.oauth2.client.registration.keycloak.scope=openid
spring.security.oauth2.client.registration.keycloak.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.keycloak.redirect-uri=http://140.245.3.74:8222/login/oauth2/code/keycloak

management.endpoint.health.probes.enabled=true
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
```

## File: src/main/resources/configurations/notification-service-k8s.properties
```
spring.data.mongodb.host=mongodb
spring.kafka.bootstrap-servers=kafka:9092
spring.mail.host=mail-dev
eureka.client.service-url.defaultZone=http://discovery:8761/eureka
```

## File: src/main/resources/configurations/notification-service.properties
```
spring.application.name=notification-service
spring.data.mongodb.username=username
spring.data.mongodb.password=password
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=notification
spring.data.mongodb.authentication-database=admin


# Kafka Broker URL
spring.kafka.bootstrap-servers=localhost:29092

# Correct consumer config keys
spring.kafka.consumer.group-id=notificationGroup
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*
spring.kafka.consumer.properties.spring.json.type.mapping=orderConfirmation:com.ecart.notification.kafka.order.OrderConfirmation
spring.kafka.consumer.properties.spring.json.value.default.type=com.ecart.notification.kafka.order.OrderConfirmation


spring.mail.host=localhost
spring.mail.port=1025
spring.mail.username=swapnil
spring.mail.password=swapnil
spring.mail.properties.mail.smtp.trust=*
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=3000
spring.mail.properties.mail.smtp.writetimeout=5000
logging.level.org.springframework=DEBUG


server.port=8040

management.endpoint.health.probes.enabled=true
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
```

## File: src/main/resources/configurations/order-service-k8s.properties
```
spring.datasource.url=jdbc:postgresql://postgresql:5432/order

application.config.customer-url=http://customer:8090/api/v1/customer
application.config.product-url=http://product:8050/api/v1/products
application.config.payment-url=http://payment:8060/api/v1/payments
spring.kafka.bootstrap-servers=kafka:9092
spring.kafka.producer.bootstrap-servers=kafka:9092
spring.kafka.consumer.bootstrap-servers=kafka:9092
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://keycloak:8080/realms/ecart-realm
eureka.client.service-url.defaultZone=http://discovery:8761/eureka
```

## File: src/main/resources/configurations/order-service.properties
```
spring.application.name=order-service


spring.datasource.url=jdbc:postgresql://localhost:5432/order
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database=postgresql
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

server.port=8070
application.config.customer-url=http://localhost:8090/api/v1/customer
application.config.product-url=http://localhost:8050/api/v1/products
application.config.payment-url=http://localhost:8060/api/v1/payments


spring.kafka.producer.bootstrap-servers=localhost:29092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
spring.kafka.producer.properties.spring.json.type.mapping=orderConfirmation:com.ecart.order.kafka.OrderConfirmation
spring.kafka.bootstrap-servers=localhost:29092
spring.kafka.consumer.bootstrap-servers=localhost:29092
spring.kafka.consumer.group-id=order-service
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=com.ecart.payment.event,com.ecart.order.payment
spring.kafka.consumer.properties.spring.json.type.mapping=paymentEvent:com.ecart.order.payment.PaymentEvent


# Keycloak Configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/ecart-realm

management.endpoint.health.probes.enabled=true
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
```

## File: src/main/resources/configurations/payment-service-k8s.properties
```
spring.datasource.url=jdbc:postgresql://postgresql:5432/payment
spring.kafka.bootstrap-servers=kafka:9092
spring.kafka.producer.bootstrap-servers=kafka:9092
application.config.product-url=http://gateway:8222/api/v1/products
eureka.client.service-url.defaultZone=http://discovery:8761/eureka
```

## File: src/main/resources/configurations/payment-service.properties
```
spring.application.name=payment-service
spring.datasource.url=jdbc:postgresql://localhost:5432/payment
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database=postgresql
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

server.port=8060


spring.kafka.producer.bootstrap-servers=localhost:29092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
spring.kafka.producer.properties.spring.json.type.mapping=paymentConfirmation:com.ecart.payment.notification.PaymentNotificationRequest,paymentEvent:com.ecart.payment.event.PaymentEvent
spring.kafka.bootstrap-servers=localhost:29092


application.config.product-url=http://localhost:8222/api/v1/products

razorpay.key_id=rzp_test_5pZoJqQBlKYB8U
razorpay.key_secret=68cqMF4ORLpMfHL3lxBwTt8n



management.endpoint.health.probes.enabled=true
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
```

## File: src/main/resources/configurations/product-service-k8s.properties
```
spring.datasource.url=jdbc:postgresql://postgresql:5432/product
spring.zipkin.base-url=http://zipkin:9411
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://keycloak:8080/realms/ecart-realm
eureka.client.service-url.defaultZone=http://discovery:8761/eureka
```

## File: src/main/resources/configurations/product-service.properties
```
spring.application.name=product-service
spring.datasource.url=jdbc:postgresql://localhost:5432/product
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.database=postgresql
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.flyway.baseline-on-migrate=true
spring.flyway.enabled=true
spring.flyway.baseline-description="init"
spring.flyway.baseline-version=0
spring.flyway.user=${spring.datasource.username}
spring.flyway.password=${spring.datasource.password}
server.port=8050


spring.zipkin.base-url=http://localhost:9422



# Keycloak Configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/ecart-realm


management.endpoint.health.probes.enabled=true
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
```

## File: src/test/java/com/e_cart/config_server/ConfigServerApplicationTests.java
```java
package com.e_cart.config_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
```
