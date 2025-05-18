package com.customer_management_system.service.impl;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.dto.customer.CustomerRequestDTO;
import com.customer_management_system.dto.customer.CustomerResponseDTO;
import com.customer_management_system.exception.BaseException;
import com.customer_management_system.exception.RecordNotFoundException;
import com.customer_management_system.mapper.customer.CustomerMapper;
import com.customer_management_system.mapper.customer.customerHasDependant.CustomerHasDependantMapper;
import com.customer_management_system.model.customer.Customer;
import com.customer_management_system.repository.customer.CustomerRepository;
import com.customer_management_system.service.validation.CustomerValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerValidation customerValidation;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerHasDependantMapper customerHasDependantMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testCreateCustomer_Success() {
        // Arrange
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setNic("123456789V");
        requestDTO.setCustomerHasDependantRequest(Arrays.asList());

        Customer newCustomer = new Customer();
        Customer mappedCustomer = new Customer();

        when(customerRepository.findByNic("123456789V")).thenReturn(null);
        when(customerMapper.mapToEntity(any(Customer.class), eq(requestDTO))).thenReturn(mappedCustomer);
        when(customerMapper.mapToDependantEntities(any())).thenReturn(Arrays.asList());
        when(customerHasDependantMapper.mapToEntities(any(), any(), any())).thenReturn(Arrays.asList());

        // Act
        CommonResponse response = customerService.createOrModify(requestDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Customer is created.", response.getMessage());
        verify(customerValidation).validateCustomerCreation(requestDTO);
        verify(customerRepository).save(mappedCustomer);
    }

    @Test
    void testUpdateCustomer_Success() {
        // Arrange
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setNic("987654321V");
        requestDTO.setCustomerHasDependantRequest(Arrays.asList());

        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerHasMobileNumbers(new HashSet<>());
        existingCustomer.setCustomerHasAddresses(new HashSet<>());
        existingCustomer.setCustomerHasDependants(new ArrayList<>());

        when(customerRepository.findByNic("987654321V")).thenReturn(existingCustomer);
        when(customerMapper.mapToEntity(any(), eq(requestDTO))).thenReturn(existingCustomer);
        when(customerMapper.mapToDependantEntities(any())).thenReturn(Arrays.asList());
        when(customerHasDependantMapper.mapToEntities(any(), any(), any())).thenReturn(Arrays.asList());

        // Act
        CommonResponse response = customerService.createOrModify(requestDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Customer is updated.", response.getMessage());
        verify(customerRepository).save(existingCustomer);
    }

    @Test
    void testCreateOrModify_ThrowsException() {
        // Arrange
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setNic("123456789V");
        requestDTO.setCustomerHasDependantRequest(Arrays.asList());

        Customer customer = new Customer();
        Customer mappedCustomer = new Customer();

        when(customerRepository.findByNic("123456789V")).thenReturn(null);
        when(customerMapper.mapToEntity(any(Customer.class), eq(requestDTO))).thenReturn(mappedCustomer);
        when(customerMapper.mapToDependantEntities(any())).thenReturn(Arrays.asList());
        when(customerHasDependantMapper.mapToEntities(any(), any(), any()))
                .thenThrow(new RuntimeException("Simulated failure"));

        // Act & Assert
        BaseException thrown = assertThrows(BaseException.class, () -> customerService.createOrModify(requestDTO));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), thrown.getErrorCode());
        assertEquals("Exception occurred while saving customer.", thrown.getMessage());
    }

    @Test
    void testGetById_CustomerFound() {
        Long customerId = 1L;
        Customer customer = new Customer();
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Mockito.when(customerMapper.mapToDTO(Mockito.any(), Mockito.eq(customer))).thenReturn(responseDTO);

        CommonResponse response = customerService.getById(customerId);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Customer found.", response.getMessage());
        assertEquals(responseDTO, response.getData());
    }

    @Test
    void testGetById_CustomerNotFound() {
        Long customerId = 1L;

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> customerService.getById(customerId));
    }

    @Test
    void testGetAllWithPage_CustomersExist() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Customer> customerList = Arrays.asList(new Customer());
        Page<Customer> page = new PageImpl<>(customerList, pageRequest, 1);

        Mockito.when(customerRepository.findAll(pageRequest)).thenReturn(page);
        Mockito.when(customerMapper.mapToDTOs(customerList)).thenReturn(Arrays.asList(new CustomerResponseDTO()));

        CommonResponse response = customerService.getAllWithPage(pageRequest);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Customers are exists.", response.getMessage());
        assertNotNull(response.getData());
    }

    @Test
    void testGetAllWithPage_NoCustomers() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Customer> emptyPage = new PageImpl<>(Arrays.asList(), pageRequest, 0);

        Mockito.when(customerRepository.findAll(pageRequest)).thenReturn(emptyPage);

        CommonResponse response = customerService.getAllWithPage(pageRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
        assertEquals("Customers are not exists.", response.getMessage());
        assertNull(response.getData());
    }
}
