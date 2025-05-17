package com.customer_management_system.mapper.customer.customerHasMobileNumber;

import com.customer_management_system.dto.customer.customerHasMobileNumber.CustomerHasMobileNumberRequestDTO;
import com.customer_management_system.dto.customer.customerHasMobileNumber.CustomerHasMobileNumberResponseDTO;
import com.customer_management_system.model.customer.Customer;
import com.customer_management_system.model.customer.CustomerHasMobileNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Slf4j
@Component
public class CustomerHasMobileNumberMapper {

    public CustomerHasMobileNumber mapToEntity(CustomerHasMobileNumber customerHasMobileNumber,
                                               Customer customer,
                                               CustomerHasMobileNumberRequestDTO dto) {
        log.info("CustomerHasDependantMapper.mapToEntity() => started.");
        customerHasMobileNumber.setMobileNumber(dto.getMobileNumber());
        customerHasMobileNumber.setCustomer(customer);
        customerHasMobileNumber.setActive(Boolean.TRUE);
        log.info("CustomerHasDependantMapper.mapToEntity() => ended.");
        return customerHasMobileNumber;
    }

    public List<CustomerHasMobileNumber> mapToEntities(List<CustomerHasMobileNumberRequestDTO> mobileNumbers, Customer customer) {
        log.info("CustomerHasDependantMapper.mapToEntities() => started.");
        return mobileNumbers.stream()
                .map(dto -> mapToEntity(
                        new CustomerHasMobileNumber(), customer, dto)
                )
                .collect(Collectors.toList());
    }

    public CustomerHasMobileNumberResponseDTO mapToDTO(CustomerHasMobileNumberResponseDTO dto,
                                                       CustomerHasMobileNumber customerHasMobileNumber) {
        log.info("CustomerHasDependantMapper.mapToDTO() => started.");
        dto.setId(customerHasMobileNumber.getId());
        dto.setMobileNumber(customerHasMobileNumber.getMobileNumber());
        log.info("CustomerHasDependantMapper.mapToDTO() => ended.");
        return dto;
    }

    public List<CustomerHasMobileNumberResponseDTO> mapToDTOs(Set<CustomerHasMobileNumber> mobileNumbers) {
        log.info("CustomerHasDependantMapper.mapToDTOs() => started.");
        return mobileNumbers.stream()
                .filter(CustomerHasMobileNumber::getActive)
                .map(n -> {
                    CustomerHasMobileNumberResponseDTO dto = new CustomerHasMobileNumberResponseDTO();
                    return mapToDTO(dto, n);
                }).collect(Collectors.toList());
    }
}
