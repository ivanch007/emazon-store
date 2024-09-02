package com.bootcamp.pragma.emazon.domain.validation;

import com.bootcamp.pragma.emazon.domain.exceptions.InvalidParameter;

public class ValidateSortingType {
    public static void validateSortingType(String sortingType) {
        if (!"asc".equalsIgnoreCase(sortingType) && !"desc".equalsIgnoreCase(sortingType)) {
            throw new InvalidParameter("El artículo debe tener entre 1 y 3 categorías.");
        }
    }
}

