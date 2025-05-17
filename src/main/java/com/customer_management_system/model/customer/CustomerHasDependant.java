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
@Table(name = "customer_has_dependant")
public class CustomerHasDependant {
    @Id
    @Column(name = "id_customer_has_dependant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id_customer")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dependant_id")
    private Customer dependant;

    @Column(name = "relationship_type")
    private String relationshipType;

    @Column(name = "active")
    private Boolean active;
}
