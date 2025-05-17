package com.customer_management_system.service.validation;

import com.customer_management_system.dto.customer.CustomerRequestDTO;
import com.customer_management_system.enums.RelationshipType;
import com.customer_management_system.exception.BadRequestException;
import com.customer_management_system.util.CommonValidation;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Component
public class CustomerValidation {

    public void validateCustomerCreation(CustomerRequestDTO customerRequest) {
        if (CommonValidation.stringNullValidation(customerRequest.getName())) {
            throw new BadRequestException("Customer name is required.");

        } else if (CommonValidation.stringNullValidation(customerRequest.getNic())) {
            throw new BadRequestException("Customer NIC number is required.");

        } else if (customerRequest.getDateOfBirth() == null) {
            throw new BadRequestException("Date of birth is required.");

        } else {
            customerRequest.getCustomerHasDependantRequest()
                    .forEach(d -> validateRelationShipType(d.getRelationshipType())
                    );
        }
    }

    public void validateRelationShipType(String relationshipType) {
        Optional<RelationshipType> any = Arrays.stream(RelationshipType.values())
                .filter(r -> r.getName().equalsIgnoreCase(relationshipType))
                .findAny();
        if (!any.isPresent()) {
            throw new BadRequestException("Invalid relationship type: " + relationshipType);
        }
    }
}
