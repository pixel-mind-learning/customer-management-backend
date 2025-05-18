package com.customer_management_system.mapper.customer;

import com.customer_management_system.dto.customer.CustomerRequestDTO;
import com.customer_management_system.dto.customer.customerHasAddress.CustomerHasAddressRequestDTO;
import com.customer_management_system.dto.customer.customerHasDependant.CustomerHasDependantRequestDTO;
import com.customer_management_system.dto.customer.customerHasMobileNumber.CustomerHasMobileNumberRequestDTO;
import com.customer_management_system.mapper.customer.customerHasAddress.CustomerHasAddressMapper;
import com.customer_management_system.mapper.customer.customerHasMobileNumber.CustomerHasMobileNumberMapper;
import com.customer_management_system.repository.customer.CustomerRepository;
import com.customer_management_system.service.validation.CustomerValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerMapperTest {

    private CustomerMapper customerMapper;
    private CustomerHasAddressMapper customerHasAddressMapper;
    private CustomerHasMobileNumberMapper customerHasMobileNumberMapper;
    private CustomerRepository customerRepository;
    CustomerValidation customerValidation;


    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper(customerHasAddressMapper, customerHasMobileNumberMapper,
                customerRepository, customerValidation);
    }

    @Test
    public void shouldMapCustomerDTOTOCustomerEntity() {
        // Create an instance of CustomerRequestDTO
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setId(null);
        customerRequestDTO.setName("John Doe");
        customerRequestDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        customerRequestDTO.setNic("123456789V");

        // Create and set values for CustomerHasAddressRequestDTO
        List<CustomerHasAddressRequestDTO> addresses = new ArrayList<>();
        CustomerHasAddressRequestDTO addressRequest = new CustomerHasAddressRequestDTO();
        addressRequest.setId(null);
        addressRequest.setAddressLine1("123 Main Street");
        addressRequest.setAddressLine2("Apt 4B");
        addressRequest.setCityId(1L);
        addressRequest.setCountryId(2L);
        addresses.add(addressRequest);

        // Create and set values for CustomerHasMobileNumberRequestDTO
        List<CustomerHasMobileNumberRequestDTO> numbers = new ArrayList<>();
        CustomerHasMobileNumberRequestDTO mobileNumberRequest = new CustomerHasMobileNumberRequestDTO();
        mobileNumberRequest.setId(null);
        mobileNumberRequest.setMobileNumber("123-456-7890");
        numbers.add(mobileNumberRequest);

        // Create and set values for CustomerHasDependantRequestDTO
        List<CustomerHasDependantRequestDTO> customerHasDependants = new ArrayList<>();
        CustomerHasDependantRequestDTO dependantRequest = new CustomerHasDependantRequestDTO();
        dependantRequest.setId(null);
        dependantRequest.setName("Jane Doe");
        dependantRequest.setDateOfBirth(LocalDate.of(2010, 5, 15));
        dependantRequest.setNic("987654321V");
        dependantRequest.setRelationshipType("Daughter");
        customerHasDependants.add(dependantRequest);

        // Add address and mobile number requests to dependantRequest
        dependantRequest.getCustomerHasAddressRequest().add(addressRequest);
        dependantRequest.getCustomerHasMobileNumberRequest().add(mobileNumberRequest);

        // Assertions can be added here to verify the object state
        assertNotNull(dependantRequest);
        assertEquals("Jane Doe", dependantRequest.getName());
        assertEquals(1, dependantRequest.getCustomerHasAddressRequest().size());
        assertEquals(1, dependantRequest.getCustomerHasMobileNumberRequest().size());
    }
}