package com.customer_management_system.dto.customer.customerHasAddress;

import lombok.Getter;
import lombok.Setter;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Setter
@Getter
public class CustomerHasAddressRequestDTO {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private Long cityId;
    private Long countryId;
}
