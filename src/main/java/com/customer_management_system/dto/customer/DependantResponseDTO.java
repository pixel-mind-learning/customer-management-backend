package com.customer_management_system.dto.customer;

import com.customer_management_system.dto.customer.customerHasAddress.CustomerHasAddressResponseDTO;
import com.customer_management_system.dto.customer.customerHasDependant.CustomerHasDependantResponseDTO;
import com.customer_management_system.dto.customer.customerHasMobileNumber.CustomerHasMobileNumberResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class DependantResponseDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String nic;
    private List<CustomerHasAddressResponseDTO> addresses = new ArrayList<>();
    private List<CustomerHasDependantResponseDTO> dependants = new ArrayList<>();
    private List<CustomerHasMobileNumberResponseDTO> mobileNumbers = new ArrayList<>();
    private String relationshipType;
}

