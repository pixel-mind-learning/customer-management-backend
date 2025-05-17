package com.customer_management_system.dto.customer.customerHasAddress;

import com.customer_management_system.dto.location.city.CityResponseDTO;
import com.customer_management_system.dto.location.country.CountryResponseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Setter
@Getter
public class CustomerHasAddressResponseDTO {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private CityResponseDTO city;
    private CountryResponseDTO country;
}
