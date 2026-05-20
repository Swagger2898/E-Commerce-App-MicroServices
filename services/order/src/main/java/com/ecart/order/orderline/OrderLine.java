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
