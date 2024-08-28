package com.bootcamp.pragma.emazon.domain.validation;

import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryInvalidDescriptionException;
import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryInvalidNameException;
import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.util.constants.CategoryConstants;

public class CategoryValidation {

    private CategoryValidation() {
    }

    public static void validateName(Category category) {
        if (category.getName() == null || category.getName().isBlank()
                || category.getName().length() > CategoryConstants.MAX_NAME_LENGTH) {
            throw new CategoryInvalidNameException();
        }
    }

    public static void validateDescription(Category category) {
        if (category.getDescription() == null || category.getDescription().isBlank()
                || category.getDescription().length() > CategoryConstants.MAX_DESCRIPTION_LENGTH) {
            throw new CategoryInvalidDescriptionException();
        }
    }

}
