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
