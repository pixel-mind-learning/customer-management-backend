package com.customer_management_system.mapper.customer.customerHasMobileNumber;

import com.customer_management_system.dto.customer.customerHasMobileNumber.CustomerHasMobileNumberRequestDTO;
import com.customer_management_system.dto.customer.customerHasMobileNumber.CustomerHasMobileNumberResponseDTO;
import com.customer_management_system.model.customer.Customer;
import com.customer_management_system.model.customer.CustomerHasMobileNumber;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerHasMobileNumberMapper {

    public CustomerHasMobileNumber mapToEntity(CustomerHasMobileNumber customerHasMobileNumber,
                                               Customer customer,
                                               CustomerHasMobileNumberRequestDTO dto) {
        customerHasMobileNumber.setMobileNumber(dto.getMobileNumber());
        customerHasMobileNumber.setCustomer(customer);
        customerHasMobileNumber.setActive(Boolean.TRUE);
        return customerHasMobileNumber;
    }

    public List<CustomerHasMobileNumber> mapToEntities(List<CustomerHasMobileNumberRequestDTO> mobileNumbers, Customer customer) {
        return mobileNumbers.stream()
                .map(dto -> mapToEntity(
                        new CustomerHasMobileNumber(), customer, dto)
                )
                .collect(Collectors.toList());
    }

    public CustomerHasMobileNumberResponseDTO mapToDTO(CustomerHasMobileNumberResponseDTO dto,
                                                       CustomerHasMobileNumber customerHasMobileNumber) {
        dto.setId(customerHasMobileNumber.getId());
        dto.setMobileNumber(customerHasMobileNumber.getMobileNumber());
        return dto;
    }

    public List<CustomerHasMobileNumberResponseDTO> mapToDTOs(Set<CustomerHasMobileNumber> mobileNumbers) {
        return mobileNumbers.stream()
                .filter(CustomerHasMobileNumber::getActive)
                .map(n -> {
                    CustomerHasMobileNumberResponseDTO dto = new CustomerHasMobileNumberResponseDTO();
                    return mapToDTO(dto, n);
                }).collect(Collectors.toList());
    }
}
