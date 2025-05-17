package com.customer_management_system.mapper.customer.customerHasAddress;

import com.customer_management_system.dto.customer.customerHasAddress.CustomerHasAddressRequestDTO;
import com.customer_management_system.dto.customer.customerHasAddress.CustomerHasAddressResponseDTO;
import com.customer_management_system.dto.location.city.CityResponseDTO;
import com.customer_management_system.dto.location.country.CountryResponseDTO;
import com.customer_management_system.exception.RecordNotFoundException;
import com.customer_management_system.mapper.location.CityMapper;
import com.customer_management_system.mapper.location.CountryMapper;
import com.customer_management_system.model.customer.Customer;
import com.customer_management_system.model.customer.CustomerHasAddress;
import com.customer_management_system.repository.location.CityRepository;
import com.customer_management_system.repository.location.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CustomerHasAddressMapper {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;
    private final CountryMapper countryMapper;

    public CustomerHasAddress mapToEntity(CustomerHasAddress customerHasAddress, Customer customer,
                                          CustomerHasAddressRequestDTO dto) {
        customerHasAddress.setAddressLine1(dto.getAddressLine1());
        customerHasAddress.setAddressLine2(dto.getAddressLine2());
        customerHasAddress.setCustomer(customer);
        customerHasAddress.setActive(Boolean.TRUE);
        customerHasAddress.setCity(cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new RecordNotFoundException("City not found for the given id."))
        );
        customerHasAddress.setCountry(countryRepository.findById(dto.getCountryId())
                .orElseThrow(() -> new RecordNotFoundException("Country not found for the given id."))
        );
        return customerHasAddress;
    }

    public List<CustomerHasAddress> mapToEntities(List<CustomerHasAddressRequestDTO> addresses, Customer customer) {
        return addresses.stream()
                .map(dto -> mapToEntity(
                        new CustomerHasAddress(), customer, dto)
                )
                .collect(Collectors.toList());
    }

    public CustomerHasAddressResponseDTO mapToDTO(CustomerHasAddressResponseDTO dto, CustomerHasAddress customerHasAddress) {
        dto.setId(customerHasAddress.getId());
        dto.setAddressLine1(customerHasAddress.getAddressLine1());
        dto.setAddressLine2(customerHasAddress.getAddressLine2());
        dto.setCity(cityMapper.mapToDTO(new CityResponseDTO(), customerHasAddress.getCity()));
        dto.setCountry(countryMapper.mapToDTO(new CountryResponseDTO(), customerHasAddress.getCountry()));
        return dto;
    }

    public List<CustomerHasAddressResponseDTO> mapToDTOs(Set<CustomerHasAddress> customerHasAddresses) {
        return customerHasAddresses.stream()
                .filter(CustomerHasAddress::getActive)
                .map(customerHasAddress -> mapToDTO(new CustomerHasAddressResponseDTO(), customerHasAddress))
                .collect(Collectors.toList());
    }
}
