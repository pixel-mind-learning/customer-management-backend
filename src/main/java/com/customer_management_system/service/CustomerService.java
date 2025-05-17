package com.customer_management_system.service;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.dto.customer.CustomerRequestDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {

    CommonResponse createOrModify(CustomerRequestDTO customerRequest);

    CommonResponse getById(Long customerId);

    CommonResponse getAllWithPage(PageRequest of);

    CommonResponse bulkUpload(MultipartFile file);
}
