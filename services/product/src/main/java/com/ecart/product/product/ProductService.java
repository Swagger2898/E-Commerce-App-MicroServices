package com.ecart.product.product;

import com.ecart.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quality for product with ID:: " +productRequest.productId());
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
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
