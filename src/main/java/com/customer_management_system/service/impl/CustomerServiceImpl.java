package com.customer_management_system.service.impl;

import com.customer_management_system.controller.response.PageResponse;
import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.dto.customer.CustomerRequestDTO;
import com.customer_management_system.dto.customer.CustomerResponseDTO;
import com.customer_management_system.exception.BaseException;
import com.customer_management_system.exception.RecordNotFoundException;
import com.customer_management_system.mapper.customer.CustomerMapper;
import com.customer_management_system.mapper.customer.customerHasDependant.CustomerHasDependantMapper;
import com.customer_management_system.model.customer.Customer;
import com.customer_management_system.repository.customer.CustomerRepository;
import com.customer_management_system.service.CustomerService;
import com.customer_management_system.service.validation.CustomerValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidation customerValidation;
    private final CustomerMapper customerMapper;
    private final CustomerHasDependantMapper customerHasDependantMapper;

    /**
     * This method is allowed to create or modify customer
     *
     * @param customerRequest {@link CustomerRequestDTO} - customer request
     * @return {@link CommonResponse} - created or modifies customer response
     * @author @maleeshasa
     */
    @Transactional
    @Override
    public CommonResponse createOrModify(CustomerRequestDTO customerRequest) {
        log.info("CustomerServiceImpl.createOrModify() => started.");
        String message;

        // Validate customer creation
        customerValidation.validateCustomerCreation(customerRequest);

        Customer customer = customerRepository.findByNic(customerRequest.getNic());
        if (customer != null) {
            message = "Customer is updated.";

            // De-activating previous customer details
            customer.getCustomerHasMobileNumbers().forEach(c -> c.setActive(Boolean.FALSE));
            customer.getCustomerHasDependants().forEach(c -> c.setActive(Boolean.FALSE));
            customer.getCustomerHasAddresses().forEach(c -> c.setActive(Boolean.FALSE));

        } else {
            message = "Customer is created.";
            customer = new Customer();
        }
        Customer mappedCustomer = customerMapper.mapToEntity(customer, customerRequest);
        List<Customer> mappedDependants = customerMapper.mapToDependantEntities(
                customerRequest.getCustomerHasDependantRequest()
        );

        try {
            mappedCustomer.setCustomerHasDependants(customerHasDependantMapper.mapToEntities(
                    customerRequest.getCustomerHasDependantRequest(),
                    mappedCustomer,
                    mappedDependants
            ));

            customerRepository.save(mappedCustomer);
            return new CommonResponse(HttpStatus.OK, message, null);

        } catch (Exception e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Exception occurred while saving customer.");
        }
    }

    /**
     * This method is allowed to fetch customer by id
     *
     * @param customerId {@link Long} - customer id
     * @return {@link CommonResponse} - fetched customer response by id
     * @author @maleeshasa
     */
    @Override
    public CommonResponse getById(Long customerId) {
        log.info("CustomerServiceImpl.getById() => started.");
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RecordNotFoundException("Customer not found."));

        return new CommonResponse(
                HttpStatus.OK,
                "Customer found.",
                customerMapper.mapToDTO(new CustomerResponseDTO(), customer)
        );
    }

    /**
     * This method is allowed to get all customers with page
     *
     * @param of {@link PageRequest} - page request
     * @return {@link CommonResponse} - fetched customers with pagination
     * @author @maleeshasa
     */
    @Override
    public CommonResponse getAllWithPage(PageRequest of) {
        log.info("CustomerServiceImpl.getAllWithPage() => started.");
        CommonResponse commonResponse = new CommonResponse();
        Page<Customer> customers = customerRepository.findAll(of);
        if (!customers.isEmpty()) {
            log.info("Customers are exists.");
            // Constructing page response as pagination
            PageResponse pageResponse = PageResponse.builder()
                    .totalPages(customers.getTotalPages())
                    .totalElements(customers.getTotalElements())
                    .currentPage(customers.getNumber())
                    .dataList(customerMapper.mapToDTOs(customers.getContent())).build();

            commonResponse.setData(pageResponse);
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setMessage("Customers are exists.");
            return commonResponse;

        } else {
            log.info("Customers are not exists.");
            commonResponse.setData(null);
            commonResponse.setStatus(HttpStatus.NO_CONTENT);
            commonResponse.setMessage("Customers are not exists.");
            return commonResponse;
        }
    }

    /**
     * This method is allowed to upload bulk customers using excel file
     *
     * @param file {@link MultipartFile} - excel file with customer details
     * @return {@link CommonResponse} - uploaded response
     * @author @maleeshasa
     */
    @Override
    public CommonResponse bulkUpload(MultipartFile file) {
        log.info("CustomerServiceImpl.bulkUpload() => started.");
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            List<CustomerRequestDTO> customerRequests = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                String name = row.getCell(1).getStringCellValue();
                String dobString = row.getCell(2).getStringCellValue();
                String nic = row.getCell(3).getStringCellValue();

                CustomerRequestDTO request = new CustomerRequestDTO();
                request.setName(name);
                request.setDateOfBirth(LocalDate.parse(dobString, formatter));
                request.setNic(nic);

                customerRequests.add(request);
            }

            for (CustomerRequestDTO dto : customerRequests) {
                createOrModify(dto);
            }

            return new CommonResponse(HttpStatus.OK, "Customers uploaded successfully", null);

        } catch (IOException e) {
            throw new BaseException(HttpStatus.BAD_GATEWAY.value(), "Failed to parse Excel file: " + e.getMessage());
        } catch (DateTimeParseException e) {
            throw new BaseException(HttpStatus.BAD_REQUEST.value(), "Invalid date format in Excel file: " + e.getMessage());
        }
    }
}
