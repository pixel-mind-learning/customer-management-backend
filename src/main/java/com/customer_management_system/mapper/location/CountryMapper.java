package com.customer_management_system.mapper.location;

import com.customer_management_system.dto.location.country.CountryResponseDTO;
import com.customer_management_system.model.location.Country;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Component
public class CountryMapper {

    public CountryResponseDTO mapToDTO(CountryResponseDTO dto, Country country) {
        dto.setId(country.getId());
        dto.setCountryCode(country.getCountryCode());
        dto.setCountryName(country.getCountryName());
        dto.setNationality(country.getNationality());
        return dto;
    }

    public List<CountryResponseDTO> mapToDTOs(List<Country> countries) {
        return countries.stream()
                .map(country -> {
                    CountryResponseDTO dto = new CountryResponseDTO();
                    dto.setId(country.getId());
                    dto.setNationality(country.getNationality());
                    dto.setCountryName(country.getCountryName());
                    dto.setCountryCode(country.getCountryCode());
                    return dto;
                }).collect(Collectors.toList());
    }
}
