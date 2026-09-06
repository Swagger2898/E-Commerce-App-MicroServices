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
src/main/java/com/ecart/product/category/Category.java
src/main/java/com/ecart/product/exception/ProductPurchaseException.java
src/main/java/com/ecart/product/handler/ErrorResponse.java
src/main/java/com/ecart/product/handler/GlobalExceptionHandler.java
src/main/java/com/ecart/product/product/Product.java
src/main/java/com/ecart/product/product/ProductController.java
src/main/java/com/ecart/product/product/ProductMapper.java
src/main/java/com/ecart/product/product/ProductPurchaseRequest.java
src/main/java/com/ecart/product/product/ProductPurchaseResponse.java
src/main/java/com/ecart/product/product/ProductRepository.java
src/main/java/com/ecart/product/product/ProductRequest.java
src/main/java/com/ecart/product/product/ProductResponse.java
src/main/java/com/ecart/product/product/ProductService.java
src/main/java/com/ecart/product/ProductApplication.java
src/main/java/com/ecart/product/SecurityConfig.java
src/main/resources/application.properties
src/main/resources/db/migration/V1__init_database.sql
src/main/resources/db/migration/V2__insert_data.sql
src/test/java/com/ecart/product/ProductApplicationTests.java
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
	<artifactId>product</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>product</name>
	<description>Product Services</description>
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
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
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
			<groupId>org.flywaydb</groupId>
			<artifactId>flyway-core</artifactId>
		</dependency>
		<dependency>
			<groupId>org.flywaydb</groupId>
			<artifactId>flyway-database-postgresql</artifactId>
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

## File: src/main/java/com/ecart/product/category/Category.java
```java
package com.ecart.product.category;

import com.ecart.product.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy="category",cascade= CascadeType.REMOVE)
    private List<Product> products;

}
```

## File: src/main/java/com/ecart/product/exception/ProductPurchaseException.java
```java
package com.ecart.product.exception;

public class ProductPurchaseException extends RuntimeException {




    public ProductPurchaseException(String s) {
        super(s);
    }
}
```

## File: src/main/java/com/ecart/product/handler/ErrorResponse.java
```java
package com.ecart.product.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
```

## File: src/main/java/com/ecart/product/handler/GlobalExceptionHandler.java
```java
package com.ecart.product.handler;

import com.ecart.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
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


@ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handle(ProductPurchaseException exp){
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(exp.getMessage());
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

## File: src/main/java/com/ecart/product/product/Product.java
```java
package com.ecart.product.product;

import com.ecart.product.category.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;

    @ManyToOne
    private Category category;
}
```

## File: src/main/java/com/ecart/product/product/ProductController.java
```java
package com.ecart.product.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service ;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }


    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody @Valid List<@Valid ProductPurchaseRequest> request
    ){
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(service.findById(productId));
    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

}
```

## File: src/main/java/com/ecart/product/product/ProductMapper.java
```java
package com.ecart.product.product;

import com.ecart.product.category.Category;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(@Valid ProductRequest request) {

       return  Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity((request.availableQuantity()))
                .category(Category.builder()
                        .id(request.categoryId())
                        .build()
                )
                .build();

    }


    public ProductResponse toProductResponse(Product product) {
    return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getAvailableQuantity(),
            product.getPrice(),
            product.getCategory().getId(),
            product.getCategory().getName(),
            product.getCategory().getDescription()


    );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity){

        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity

        );

    }

}
```

## File: src/main/java/com/ecart/product/product/ProductPurchaseRequest.java
```java
package com.ecart.product.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message="Product is mandatory")
        Integer productId,
        @Positive(message = "Quantity should be positive")
        double quantity

) {

}
```

## File: src/main/java/com/ecart/product/product/ProductPurchaseResponse.java
```java
package com.ecart.product.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(

        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity

) {

}
```

## File: src/main/java/com/ecart/product/product/ProductRepository.java
```java
package com.ecart.product.product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByIdInOrderById(List<Integer> productIds);

    @Modifying
    @Query("""
            update Product p
            set p.availableQuantity = p.availableQuantity - :requested
            where p.id = :id
              and p.availableQuantity >= :requested
            """)
    int decrementAvailableQuantityIfEnoughStock(@Param("id") Integer id, @Param("requested") double requested);
}
```

## File: src/main/java/com/ecart/product/product/ProductRequest.java
```java
package com.ecart.product.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available quantity should be positive")
        double availableQuantity,
        @Positive(message = "Price should be positive")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
```

## File: src/main/java/com/ecart/product/product/ProductResponse.java
```java
package com.ecart.product.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

## File: src/main/java/com/ecart/product/product/ProductService.java
```java
package com.ecart.product.product;

import com.ecart.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(@Valid ProductRequest request) {

        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    @Transactional
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = request
                .stream()
                .map(ProductPurchaseRequest :: productId)
                .toList();

        List<Product> storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size()!=storedProducts.size()){
            throw new ProductPurchaseException("One or more products don't exist");
        }
        List<ProductPurchaseRequest> storedRequest =request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        for(int i=0;i<storedProducts.size();i++){
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);
            int updatedRows = repository.decrementAvailableQuantityIfEnoughStock(product.getId(), productRequest.quantity());
            if(updatedRows == 0){
                throw new ProductPurchaseException("Insufficient stock quality for product with ID:: " +productRequest.productId());
            }
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        return purchasedProducts;

    }

    public ProductResponse findById(Integer productId) {
       return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with ID:: "+productId));
    }

    public List<ProductResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper:: toProductResponse)
                .collect(Collectors.toList());
    }
}
```

## File: src/main/java/com/ecart/product/ProductApplication.java
```java
package com.ecart.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {


		SpringApplication.run(ProductApplication.class, args);


	}

}
```

## File: src/main/java/com/ecart/product/SecurityConfig.java
```java
package com.ecart.product;

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
spring.application.name=product-service
spring.config.import=optional:configserver:http://localhost:8888
```

## File: src/main/resources/db/migration/V1__init_database.sql
```sql
create table if not exists category
(
id integer not null primary key,
description varchar(255),
name varchar(255)
);

create table if not exists product
(
id integer not null primary key,
description varchar(255),
name varchar(255),
available_quantity double precision not null,
price numeric(38,2),
category_id integer constraint gfhjskabfdjfkl references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;
```

## File: src/main/resources/db/migration/V2__insert_data.sql
```sql
-- Insert into category table
INSERT INTO category (id, description, name)
VALUES
  (nextval('category_seq'), 'Category for electronics products', 'Electronics'),
  (nextval('category_seq'), 'Category for clothing products', 'Clothing'),
  (nextval('category_seq'), 'Category for home appliances', 'Home Appliances');

-- Insert into product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
  (nextval('product_seq'), 'Smartphone with 6GB RAM and 128GB storage', 'Smartphone', 150, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
  (nextval('product_seq'), 'T-shirt made of cotton', 'T-shirt', 200, 19.99, (SELECT id FROM category WHERE name = 'Clothing')),
  (nextval('product_seq'), 'Refrigerator with 300L capacity', 'Refrigerator', 50, 499.99, (SELECT id FROM category WHERE name = 'Home Appliances')),
  (nextval('product_seq'), 'Wireless Bluetooth Headphones', 'Headphones', 75, 99.99, (SELECT id FROM category WHERE name = 'Electronics')),
  (nextval('product_seq'), 'Winter Jacket', 'Jacket', 40, 129.99, (SELECT id FROM category WHERE name = 'Clothing'));
```

## File: src/test/java/com/ecart/product/ProductApplicationTests.java
```java
package com.ecart.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

## File: src/test/resources/application.properties
```
spring.application.name=product-service
spring.cloud.config.enabled=false
spring.cloud.config.import-check.enabled=false
eureka.client.enabled=false

spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/dummy/jwks
```
