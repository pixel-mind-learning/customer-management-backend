package com.customer_management_system.mapper.location;

import com.customer_management_system.dto.location.city.CityResponseDTO;
import com.customer_management_system.model.location.City;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Component
public class CityMapper {

    public CityResponseDTO mapToDTO(CityResponseDTO dto, City city) {
        dto.setId(city.getId());
        dto.setCityName(city.getCityName());
        dto.setCityZipCode(city.getCityZipCode());
        return dto;
    }

    public List<CityResponseDTO> mapToDTOs(List<City> cities) {
        return cities.stream()
                .map(city -> {
                    CityResponseDTO dto = new CityResponseDTO();
                    dto.setId(city.getId());
                    dto.setCityName(city.getCityName());
                    dto.setCityZipCode(city.getCityZipCode());
                    return dto;
                }).collect(Collectors.toList());
    }
}
