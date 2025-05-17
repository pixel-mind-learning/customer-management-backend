package com.customer_management_system.model.customer;

import com.customer_management_system.model.location.City;
import com.customer_management_system.model.location.Country;
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
@Table(name = "customer_has_address")
public class CustomerHasAddress {
    @Id
    @Column(name = "id_customer_has_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_line_1", length = 45)
    private String addressLine1;

    @Column(name = "address_line_2", length = 45)
    private String addressLine2;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "city_id_city")
    private City city;

    @ManyToOne
    @JoinColumn(name = "country_id_country")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "customer_id_customer")
    private Customer customer;
}
