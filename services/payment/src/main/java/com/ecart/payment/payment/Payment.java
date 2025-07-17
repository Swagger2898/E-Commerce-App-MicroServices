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
    private String orderReference; // ✅ Needed to look up payments via orderRef
    private String paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @CreatedDate
    @Column(updatable=false, nullable=false)
    private LocalDateTime createdAt;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

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
