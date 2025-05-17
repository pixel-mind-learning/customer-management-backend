package com.customer_management_system.dto.customer;

import com.customer_management_system.dto.customer.customerHasAddress.CustomerHasAddressResponseDTO;
import com.customer_management_system.dto.customer.customerHasMobileNumber.CustomerHasMobileNumberResponseDTO;
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
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String nic;
    private List<CustomerHasAddressResponseDTO> addresses = new ArrayList<>();
    private List<DependantResponseDTO> dependants = new ArrayList<>();
    private List<CustomerHasMobileNumberResponseDTO> mobileNumbers = new ArrayList<>();
}

