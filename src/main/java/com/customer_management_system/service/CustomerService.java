package com.customer_management_system.service;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.dto.customer.CustomerRequestDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
public interface CustomerService {

    /**
     * This method is allowed to create or modify customer
     *
     * @param customerRequest {@link CustomerRequestDTO} - customer request
     * @return {@link CommonResponse} - created or modifies customer response
     * @author @maleeshasa
     */
    CommonResponse createOrModify(CustomerRequestDTO customerRequest);

    /**
     * This method is allowed to fetch customer by id
     *
     * @param customerId {@link Long} - customer id
     * @return {@link CommonResponse} - fetched customer response by id
     * @author @maleeshasa
     */
    CommonResponse getById(Long customerId);

    /**
     * This method is allowed to get all customers with page
     *
     * @param of {@link PageRequest} - page request
     * @return {@link CommonResponse} - fetched customers with pagination
     * @author @maleeshasa
     */
    CommonResponse getAllWithPage(PageRequest of);

    /**
     * This method is allowed to upload bulk customers using excel file
     *
     * @param file {@link MultipartFile} - excel file with customer details
     * @return {@link CommonResponse} - uploaded response
     * @author @maleeshasa
     */
    CommonResponse bulkUpload(MultipartFile file);
}
