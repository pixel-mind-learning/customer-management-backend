package com.customer_management_system.service;

import com.customer_management_system.dto.CommonResponse;

public interface LocationService {

    CommonResponse getAllCountries();

    CommonResponse getAllCities();
}
