package com.customer_management_system.enums;

import lombok.Getter;

@Getter
public enum RelationshipType {
    SPOUSE("Spouse"),
    CHILD("Child"),
    PARENT("Parent");

    private final String name;

    RelationshipType(String name) {
        this.name = name;
    }
}
