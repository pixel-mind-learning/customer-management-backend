package com.customer_management_system.controller;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.dto.customer.CustomerRequestDTO;
import com.customer_management_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/customer/v1")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * This method is allowed to create or modify customer
     *
     * @param customerRequest {@link CustomerRequestDTO} - customer request
     * @return {@link ResponseEntity<CommonResponse>} - created or modifies customer response
     * @author @maleeshasa
     */
    @PostMapping(value = "/create-or-modify")
    public ResponseEntity<CommonResponse> createOrModify(@RequestBody CustomerRequestDTO customerRequest) {
        log.info("CustomerController.createOrModify() => started.");
        return ResponseEntity.ok(customerService.createOrModify(customerRequest));
    }

    /**
     * This method is allowed to fetch customer by id
     *
     * @param customerId {@link Long} - customer id
     * @return {@link ResponseEntity<CommonResponse>} - fetched customer response by id
     * @author @maleeshasa
     */
    @GetMapping(value = "/get-by-id/{customerId}")
    public ResponseEntity<CommonResponse> getById(@PathVariable Long customerId) {
        log.info("CustomerController.getById() => started.");
        return ResponseEntity.ok(customerService.getById(customerId));
    }

    /**
     * This method is allowed to get all customers with page
     *
     * @param page - page
     * @param size - size
     * @return {@link ResponseEntity<CommonResponse>} - fetched customers with pagination
     * @author @maleeshasa
     */
    @GetMapping(value = "/get-all-with-page")
    public ResponseEntity<CommonResponse> getAllWithPage(@RequestParam(value = "page") int page,
                                                         @RequestParam(value = "size") int size) {
        log.info("CustomerController.getAllWithPage() => started.");
        return ResponseEntity.ok(customerService.getAllWithPage(PageRequest.of(page, size)));
    }

    /**
     * This method is allowed to upload bulk customers using excel file
     *
     * @param file {@link MultipartFile} - excel file with customer details
     * @return {@link ResponseEntity<CommonResponse>} - uploaded response
     * @author @maleeshasa
     */
    @PostMapping(value = "/bulk-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonResponse> bulkUpload(@RequestPart("file") MultipartFile file) {
        log.info("CustomerController.bulkUpload() => started.");
        return ResponseEntity.ok(customerService.bulkUpload(file));
    }
}
