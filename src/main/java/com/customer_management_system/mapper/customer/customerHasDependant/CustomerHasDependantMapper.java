package com.customer_management_system.mapper.customer.customerHasDependant;

import com.customer_management_system.dto.customer.customerHasDependant.CustomerHasDependantRequestDTO;
import com.customer_management_system.model.customer.Customer;
import com.customer_management_system.model.customer.CustomerHasDependant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerHasDependantMapper {

    public CustomerHasDependant mapToEntity(CustomerHasDependant customerHasDependant,
                                            List<CustomerHasDependantRequestDTO> dependantRequests,
                                            Customer customer,
                                            Customer dependant) {
        customerHasDependant.setActive(Boolean.TRUE);
        customerHasDependant.setCustomer(customer);
        customerHasDependant.setDependant(dependant);
        for (CustomerHasDependantRequestDTO dependantRequest : dependantRequests) {
            if (dependant.getNic().equalsIgnoreCase(dependantRequest.getNic())) {
                customerHasDependant.setRelationshipType(dependantRequest.getRelationshipType());
            }
        }
        return customerHasDependant;
    }

    public List<CustomerHasDependant> mapToEntities(List<CustomerHasDependantRequestDTO> dependantRequests,
                                                    Customer customer,
                                                    List<Customer> dependants) {
        return dependants.stream().map(dependant ->
                mapToEntity(new CustomerHasDependant(), dependantRequests, customer, dependant)
        ).collect(Collectors.toList());
    }
}
