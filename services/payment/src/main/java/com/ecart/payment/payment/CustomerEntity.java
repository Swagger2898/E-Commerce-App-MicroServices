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
