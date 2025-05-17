package com.customer_management_system.dto.location.city;

import lombok.Getter;
import lombok.Setter;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Getter
@Setter
public class CityResponseDTO {
    private Long id;
    private String cityName;
    private String cityZipCode;
}
