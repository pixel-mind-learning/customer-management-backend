package com.customer_management_system.dto.location.country;

import lombok.Getter;
import lombok.Setter;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Setter
@Getter
public class CountryResponseDTO {
    private Long id;
    private String countryName;
    private String countryCode;
    private String nationality;
}
