package com.customer_management_system.mapper.customer.customerHasDependant;

import com.customer_management_system.dto.customer.customerHasDependant.CustomerHasDependantRequestDTO;
import com.customer_management_system.model.customer.Customer;
import com.customer_management_system.model.customer.CustomerHasDependant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Slf4j
@Component
public class CustomerHasDependantMapper {

    public CustomerHasDependant mapToEntity(CustomerHasDependant customerHasDependant,
                                            List<CustomerHasDependantRequestDTO> dependantRequests,
                                            Customer customer,
                                            Customer dependant) {
        log.info("CustomerHasDependantMapper.mapToEntity() => started.");
        customerHasDependant.setActive(Boolean.TRUE);
        customerHasDependant.setCustomer(customer);
        customerHasDependant.setDependant(dependant);
        for (CustomerHasDependantRequestDTO dependantRequest : dependantRequests) {
            if (dependant.getNic().equalsIgnoreCase(dependantRequest.getNic())) {
                customerHasDependant.setRelationshipType(dependantRequest.getRelationshipType());
            }
        }
        log.info("CustomerHasDependantMapper.mapToEntity() => ended.");
        return customerHasDependant;
    }

    public List<CustomerHasDependant> mapToEntities(List<CustomerHasDependantRequestDTO> dependantRequests,
                                                    Customer customer,
                                                    List<Customer> dependants) {
        log.info("CustomerHasDependantMapper.mapToEntities() => started.");
        return dependants.stream().map(dependant ->
                mapToEntity(new CustomerHasDependant(), dependantRequests, customer, dependant)
        ).collect(Collectors.toList());
    }
}
