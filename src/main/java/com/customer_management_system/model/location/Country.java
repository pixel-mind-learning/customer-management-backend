package com.customer_management_system.model.location;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Setter
@Getter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "id_country")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name", length = 40)
    private String countryName;

    @Column(name = "country_code", length = 10)
    private String countryCode;

    @Column(name = "country_nationality", length = 40)
    private String nationality;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cities = new ArrayList<>();
}
