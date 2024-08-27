package com.bootcamp.pragma.emazon.domain.validation;

import com.bootcamp.pragma.emazon.domain.exceptions.CategoryInvalidDescriptionException;
import com.bootcamp.pragma.emazon.domain.exceptions.CategoryInvalidNameException;
import com.bootcamp.pragma.emazon.domain.model.Category;

public class CategoryValidation {

    private CategoryValidation(){}

    public static void validateName(Category category){
        if (category.getName() == null || category.getName().isBlank() || category.getName().length() > 50) {
            throw new CategoryInvalidNameException();
        }
    }

    public static void validateDescription(Category category){
        if (category.getDescription() == null || category.getDescription().isBlank() || category.getDescription().length() > 90) {
            throw new CategoryInvalidDescriptionException();
        }
    }

}
