package com.customer_management_system.service;

import com.customer_management_system.dto.CommonResponse;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
public interface LocationService {

    /**
     * This method is allowed to fetch all countries
     *
     * @return {@link CommonResponse} - fetched countries response
     * @author @maleeshasa
     */
    CommonResponse getAllCountries();

    /**
     * This method is allowed to fetch all cities
     *
     * @return {@link CommonResponse} - fetched cities response
     * @author @maleeshasa
     */
    CommonResponse getAllCities();
}
