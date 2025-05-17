package com.customer_management_system.service.impl;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.mapper.location.CityMapper;
import com.customer_management_system.mapper.location.CountryMapper;
import com.customer_management_system.model.location.City;
import com.customer_management_system.model.location.Country;
import com.customer_management_system.repository.location.CityRepository;
import com.customer_management_system.repository.location.CountryRepository;
import com.customer_management_system.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final CityMapper cityMapper;

    @Override
    public CommonResponse getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        if (!countries.isEmpty()) {
            return new CommonResponse(HttpStatus.OK, "Countries retrieved successfully", countryMapper.mapToDTOs(countries));
        } else {
            return new CommonResponse(HttpStatus.NO_CONTENT, "Countries not found", null);
        }
    }

    @Override
    public CommonResponse getAllCities() {
        List<City> cities = cityRepository.findAll();
        if (!cities.isEmpty()) {
            return new CommonResponse(HttpStatus.OK, "Cities retrieved successfully", cityMapper.mapToDTOs(cities));
        } else {
            return new CommonResponse(HttpStatus.NO_CONTENT, "Cities not found", null);
        }
    }
}
