package com.customer_management_system.mapper.customer;

import com.customer_management_system.dto.customer.CustomerRequestDTO;
import com.customer_management_system.dto.customer.CustomerResponseDTO;
import com.customer_management_system.dto.customer.DependantResponseDTO;
import com.customer_management_system.dto.customer.customerHasDependant.CustomerHasDependantRequestDTO;
import com.customer_management_system.mapper.customer.customerHasAddress.CustomerHasAddressMapper;
import com.customer_management_system.mapper.customer.customerHasMobileNumber.CustomerHasMobileNumberMapper;
import com.customer_management_system.model.customer.Customer;
import com.customer_management_system.model.customer.CustomerHasDependant;
import com.customer_management_system.repository.customer.CustomerRepository;
import com.customer_management_system.service.validation.CustomerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CustomerMapper {

    private final CustomerHasAddressMapper customerHasAddressMapper;
    private final CustomerHasMobileNumberMapper customerHasMobileNumberMapper;
    private final CustomerRepository customerRepository;
    private final CustomerValidation customerValidation;

    public Customer mapToEntity(Customer customer, CustomerRequestDTO dto) {
        customer.setName(dto.getName());
        customer.setNic(dto.getNic());
        customer.setDateOfBirth(dto.getDateOfBirth());

        customer.setCustomerHasAddresses(
                new HashSet<>(customerHasAddressMapper.mapToEntities(dto.getCustomerHasAddressRequest(), customer))
        );

        customer.setCustomerHasMobileNumbers(
                new HashSet<>(customerHasMobileNumberMapper.mapToEntities(dto.getCustomerHasMobileNumberRequest(), customer))
        );
        return customer;
    }

    public List<Customer> mapToDependantEntities(List<CustomerHasDependantRequestDTO> dependants) {
        return dependants.stream()
                .map(dto -> {
                    Customer dependant = customerRepository.findByNic(dto.getNic());
                    if (dependant == null) {
                        dependant = new Customer();
                    } else {
                        dependant.getCustomerHasMobileNumbers().forEach(c -> c.setActive(Boolean.FALSE));
                        dependant.getCustomerHasDependants().forEach(c -> c.setActive(Boolean.FALSE));
                        dependant.getCustomerHasAddresses().forEach(c -> c.setActive(Boolean.FALSE));
                    }
                    CustomerRequestDTO customerReq = new CustomerRequestDTO();
                    customerReq.setName(dto.getName());
                    customerReq.setNic(dto.getNic());
                    customerReq.setDateOfBirth(dto.getDateOfBirth());
                    customerReq.setCustomerHasAddressRequest(dto.getCustomerHasAddressRequest());
                    customerReq.setCustomerHasMobileNumberRequest(dto.getCustomerHasMobileNumberRequest());

                    // Validate dependant
                    customerValidation.validateCustomerCreation(customerReq);

                    return mapToEntity(dependant, customerReq);
                })
                .collect(Collectors.toList());
    }

    public List<DependantResponseDTO> mapToDependantDTOs(List<CustomerHasDependant> dependants) {
        return dependants.stream()
                .filter(CustomerHasDependant::getActive)
                .map(dependant -> {
                    DependantResponseDTO dto = new DependantResponseDTO();
                    Customer dependantObj = dependant.getDependant();
                    dto.setId(dependantObj.getId());
                    dto.setName(dependantObj.getName());
                    dto.setNic(dependantObj.getNic());
                    dto.setDateOfBirth(dependantObj.getDateOfBirth());
                    dto.setRelationshipType(dependant.getRelationshipType());
                    dto.setAddresses(customerHasAddressMapper.mapToDTOs(dependantObj.getCustomerHasAddresses()));
                    dto.setMobileNumbers(customerHasMobileNumberMapper.mapToDTOs(dependantObj.getCustomerHasMobileNumbers()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public CustomerResponseDTO mapToDTO(CustomerResponseDTO dto, Customer customer) {
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setNic(customer.getNic());
        dto.setDateOfBirth(customer.getDateOfBirth());
        dto.setAddresses(customerHasAddressMapper.mapToDTOs(customer.getCustomerHasAddresses()));
        dto.setDependants(mapToDependantDTOs(customer.getCustomerHasDependants()));
        dto.setMobileNumbers(customerHasMobileNumberMapper.mapToDTOs(customer.getCustomerHasMobileNumbers()));
        return dto;
    }

    public List<CustomerResponseDTO> mapToDTOs(List<Customer> customers) {
        return customers.stream()
                .map(customer -> {
                    CustomerResponseDTO dto = new CustomerResponseDTO();
                    return mapToDTO(dto, customer);
                })
                .collect(Collectors.toList());
    }
}
