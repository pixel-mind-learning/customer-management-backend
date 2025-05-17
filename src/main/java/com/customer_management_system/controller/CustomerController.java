package com.customer_management_system.controller;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.dto.customer.CustomerRequestDTO;
import com.customer_management_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/customer/v1")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/create-or-modify")
    public ResponseEntity<CommonResponse> createOrModify(@RequestBody CustomerRequestDTO customerRequest) {
        return ResponseEntity.ok(customerService.createOrModify(customerRequest));
    }

    @GetMapping(value = "/get-by-id/{customerId}")
    public ResponseEntity<CommonResponse> getById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getById(customerId));
    }

    @GetMapping(value = "/get-all-with-page")
    public ResponseEntity<CommonResponse> getAllWithPage(@RequestParam(value = "page") int page,
                                                         @RequestParam(value = "size") int size) {
        return ResponseEntity.ok(customerService.getAllWithPage(PageRequest.of(page, size)));
    }

    @PostMapping(value = "/bulk-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonResponse> bulkUpload(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(customerService.bulkUpload(file));
    }
}
