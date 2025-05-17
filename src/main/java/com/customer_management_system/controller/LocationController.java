package com.customer_management_system.controller;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/customer/location/v1")
public class LocationController {

    private final LocationService locationService;

    @GetMapping(value = "/get-all-cities")
    public ResponseEntity<CommonResponse> getAllCities() {
        return ResponseEntity.ok(locationService.getAllCities());
    }

    @GetMapping(value = "/get-all-countries")
    public ResponseEntity<CommonResponse> getAllCountries() {
        return ResponseEntity.ok(locationService.getAllCountries());
    }
}
