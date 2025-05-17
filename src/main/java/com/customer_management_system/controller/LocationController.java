package com.customer_management_system.controller;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/customer/location/v1")
public class LocationController {

    private final LocationService locationService;

    /**
     * This method is allowed to fetch all cities
     *
     * @return {@link ResponseEntity<CommonResponse>} - fetched cities response
     * @author @maleeshasa
     */
    @GetMapping(value = "/get-all-cities")
    public ResponseEntity<CommonResponse> getAllCities() {
        log.info("LocationController.getAllCities() => started.");
        return ResponseEntity.ok(locationService.getAllCities());
    }

    /**
     * This method is allowed to fetch all countries
     *
     * @return {@link ResponseEntity<CommonResponse>} - fetched countries response
     * @author @maleeshasa
     */
    @GetMapping(value = "/get-all-countries")
    public ResponseEntity<CommonResponse> getAllCountries() {
        log.info("LocationController.getAllCountries() => started.");
        return ResponseEntity.ok(locationService.getAllCountries());
    }
}
