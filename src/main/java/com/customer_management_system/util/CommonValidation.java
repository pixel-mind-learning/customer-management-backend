package com.customer_management_system.util;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
public class CommonValidation {

    public static boolean stringNullValidation(String inputString) {
        return inputString == null || inputString.isEmpty();
    }

    public static boolean integerNullValidation(Integer inputValue) {
        return inputValue == null;
    }
}
