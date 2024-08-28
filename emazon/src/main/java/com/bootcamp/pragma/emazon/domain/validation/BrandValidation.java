package com.bootcamp.pragma.emazon.domain.validation;

import com.bootcamp.pragma.emazon.domain.exceptions.brandexceptions.BrandInvalidDescriptionException;
import com.bootcamp.pragma.emazon.domain.exceptions.brandexceptions.BrandInvalidNameException;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import com.bootcamp.pragma.emazon.domain.util.constants.BrandConstants;

public class BrandValidation {

    private BrandValidation() {
    }

    public static void validateName(Brand brand) {
        if (brand.getName() == null || brand.getName().isBlank()
                || brand.getName().length() > BrandConstants.MAX_NAME_LENGTH) {
            throw new BrandInvalidNameException();
        }
    }

    public static void validateDescription(Brand brand) {
        if (brand.getDescription() == null || brand.getDescription().isBlank()
                || brand.getDescription().length() > BrandConstants.MAX_DESCRIPTION_LENGTH ){
            throw new BrandInvalidDescriptionException();
        }
    }
}
