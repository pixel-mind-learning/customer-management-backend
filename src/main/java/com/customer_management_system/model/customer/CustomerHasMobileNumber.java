package com.customer_management_system.model.customer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Setter
@Getter
@Entity
@Table(name = "customer_has_mobile_number")
public class CustomerHasMobileNumber {
    @Id
    @Column(name = "id_customer_has_mobile_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id_customer")
    private Customer customer;

    @Column(name = "active")
    private Boolean active;
}
