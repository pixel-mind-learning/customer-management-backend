package com.customer_management_system.dto.customer.customerHasDependant;

import com.customer_management_system.dto.customer.customerHasMobileNumber.CustomerHasMobileNumberRequestDTO;
import com.customer_management_system.dto.customer.customerHasAddress.CustomerHasAddressRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Setter
@Getter
public class CustomerHasDependantRequestDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String nic;
    private List<CustomerHasAddressRequestDTO> customerHasAddressRequest = new ArrayList<>();
    private List<CustomerHasMobileNumberRequestDTO> customerHasMobileNumberRequest = new ArrayList<>();
    private String relationshipType;
}
