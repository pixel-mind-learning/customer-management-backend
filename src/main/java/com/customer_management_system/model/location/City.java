package com.customer_management_system.model.location;

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
@Table(name = "city")
public class City {
    @Id
    @Column(name = "id_city")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name", length = 40)
    private String cityName;

    @Column(name = "city_zip_code", length = 40)
    private String cityZipCode;

    @JoinColumn(name = "country_id_country")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Country country;
}
