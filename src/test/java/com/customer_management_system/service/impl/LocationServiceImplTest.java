package com.customer_management_system.service.impl;

import com.customer_management_system.dto.CommonResponse;
import com.customer_management_system.mapper.location.CityMapper;
import com.customer_management_system.mapper.location.CountryMapper;
import com.customer_management_system.repository.location.CityRepository;
import com.customer_management_system.repository.location.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private LocationServiceImpl locationService;

//    @Test
//    void testGetAllCountries_Success() {
//        List<Country> countries = Arrays.asList(new Country());
//        List<Object> countryDTOs = Arrays.asList(new Object());
//
//        when(countryRepository.findAll()).thenReturn(countries);
//        when(countryMapper.mapToDTOs(countries)).thenReturn(countryDTOs);
//
//        CommonResponse response = locationService.getAllCountries();
//
//        assertEquals(HttpStatus.OK, response.getStatus());
//        assertEquals("Countries retrieved successfully", response.getMessage());
//        assertEquals(countryDTOs, response.getData());
//    }

    @Test
    void testGetAllCountries_Empty() {
        when(countryRepository.findAll()).thenReturn(Collections.emptyList());

        CommonResponse response = locationService.getAllCountries();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
        assertEquals("Countries not found", response.getMessage());
        assertNull(response.getData());
    }

//    @Test
//    void testGetAllCities_Success() {
//        List<City> cities = Arrays.asList(new City());
//        List<Object> cityDTOs = Arrays.asList(new Object());
//
//        when(cityRepository.findAll()).thenReturn(cities);
//        when(cityMapper.mapToDTOs(cities)).thenReturn(cityDTOs);
//
//        CommonResponse response = locationService.getAllCities();
//
//        assertEquals(HttpStatus.OK, response.getStatus());
//        assertEquals("Cities retrieved successfully", response.getMessage());
//        assertEquals(cityDTOs, response.getData());
//    }

    @Test
    void testGetAllCities_Empty() {
        when(cityRepository.findAll()).thenReturn(Collections.emptyList());

        CommonResponse response = locationService.getAllCities();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
        assertEquals("Cities not found", response.getMessage());
        assertNull(response.getData());
    }
}