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
src/main/java/com/e_cart/customer/customer/Address.java
src/main/java/com/e_cart/customer/customer/Customer.java
src/main/java/com/e_cart/customer/customer/CustomerController.java
src/main/java/com/e_cart/customer/customer/CustomerMapper.java
src/main/java/com/e_cart/customer/customer/CustomerRepository.java
src/main/java/com/e_cart/customer/customer/CustomerRequest.java
src/main/java/com/e_cart/customer/customer/CustomerResponse.java
src/main/java/com/e_cart/customer/customer/CustomerService.java
src/main/java/com/e_cart/customer/CustomerApplication.java
src/main/java/com/e_cart/customer/exception/CustomerNotFoundException.java
src/main/java/com/e_cart/customer/handler/ErrorResponse.java
src/main/java/com/e_cart/customer/handler/GlobalExceptionHandler.java
src/main/java/com/e_cart/customer/SecurityConfig.java
src/main/resources/application.properties
src/test/java/com/e_cart/customer/CustomerApplicationTests.java
src/test/resources/application.properties
```

# Files

## File: pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<packaging>jar</packaging>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.e-cart</groupId>
	<artifactId>customer</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>customer</name>
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
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
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



		<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.36</version>
			<scope>provided</scope>
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

## File: src/main/java/com/e_cart/customer/customer/Address.java
```java
package com.e_cart.customer.customer;


import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {

    private String street;

    private String houseNumber;

    private String zipCode;

}
```

## File: src/main/java/com/e_cart/customer/customer/Customer.java
```java
package com.e_cart.customer.customer;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Customer {
@Id
private String id;

private String firstname;

private String lastname;

private String email;

private Address address;


}
```

## File: src/main/java/com/e_cart/customer/customer/CustomerController.java
```java
package com.e_cart.customer.customer;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

private final CustomerService service ;

@PostMapping
    public ResponseEntity<String> createCustomer(
        @RequestBody @Valid CustomerRequest request
        ){
    return ResponseEntity.ok(service.createCustomer(request));
}

@PutMapping
    public ResponseEntity<?> updateCustomer(
            @RequestBody @Valid CustomerRequest request
){
    service.updateCustomer(request);
    return ResponseEntity.accepted().build();
}

@GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
    return ResponseEntity.ok(service.findAllCustomers());
}

@GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") String customerId
){
    return ResponseEntity.ok(service.existById(customerId));
}

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(service.findById(customerId));
    }


    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("customer-id") String customerId
    ){
    service.deleteCustomer(customerId);
    return ResponseEntity.accepted().build();
    }
}
```

## File: src/main/java/com/e_cart/customer/customer/CustomerMapper.java
```java
package com.e_cart.customer.customer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(@Valid CustomerRequest request) {
  if(request==null){
      return null ;
  }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .email(request.email())
                .lastname(request.lastname())
                .address(request.address())
                .build();

    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );

    }
}
```

## File: src/main/java/com/e_cart/customer/customer/CustomerRepository.java
```java
package com.e_cart.customer.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {


}
```

## File: src/main/java/com/e_cart/customer/customer/CustomerRequest.java
```java
package com.e_cart.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

         String id,
         @NotNull(message="Customer firstname is required")
         String firstname,
         @NotNull(message="Customer lastname is required")
         String lastname,
         @NotNull(message="Customer email is required")
         @Email(message="Customer email is not a valid email address")
         String email,

         Address address

) {
}
```

## File: src/main/java/com/e_cart/customer/customer/CustomerResponse.java
```java
package com.e_cart.customer.customer;

public record CustomerResponse (

    String id,
    String firstname,
    String lastname,
    String email,
    Address address
){
}
```

## File: src/main/java/com/e_cart/customer/customer/CustomerService.java
```java
package com.e_cart.customer.customer;

import com.e_cart.customer.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        // Save the customer and return the generated ID as a String
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();  // This will return a String
    }


    public void updateCustomer(@Valid CustomerRequest request) {

        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("cannot update customer:: NO CUSTOMER FOUND WITH THE PROVIDED ID:: %s", request.id())

                ));
        mergerCustomer(customer, request);
        customerRepository.save(customer);

    }

    private void mergerCustomer(Customer customer, @Valid CustomerRequest request) {

        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname((request.firstname()));
        }
        if (StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname((request.lastname()));
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail((request.email()));
        }
        if (request.address() != null) {
            customer.setAddress((request.address()));
        }

    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(format("No customer found with the provided ID :: %s",customerId)));

    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
```

## File: src/main/java/com/e_cart/customer/CustomerApplication.java
```java
package com.e_cart.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
```

## File: src/main/java/com/e_cart/customer/exception/CustomerNotFoundException.java
```java
package com.e_cart.customer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

//@Data
//@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException{

    private final String msg;

    public CustomerNotFoundException(String msg){
        this.msg=msg;
    }
}
```

## File: src/main/java/com/e_cart/customer/handler/ErrorResponse.java
```java
package com.e_cart.customer.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
```

## File: src/main/java/com/e_cart/customer/handler/GlobalExceptionHandler.java
```java
package com.e_cart.customer.handler;

import com.e_cart.customer.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exp){
    return ResponseEntity
            .status(NOT_FOUND)
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

## File: src/main/java/com/e_cart/customer/SecurityConfig.java
```java
package com.e_cart.customer;

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
spring.application.name=customer-service
spring.config.import=optional:configserver:http://localhost:8888
```

## File: src/test/java/com/e_cart/customer/CustomerApplicationTests.java
```java
package com.e_cart.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

## File: src/test/resources/application.properties
```
spring.application.name=customer-service
spring.cloud.config.enabled=false
spring.cloud.config.import-check.enabled=false
eureka.client.enabled=false

spring.data.mongodb.uri=mongodb://localhost:27017/testdb

spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/dummy/jwks
```
