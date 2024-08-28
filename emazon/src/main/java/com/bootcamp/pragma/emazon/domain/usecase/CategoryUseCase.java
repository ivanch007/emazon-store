package com.bootcamp.pragma.emazon.domain.usecase;

import com.bootcamp.pragma.emazon.domain.api.CategoryServicePort;
import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryNameAlreadyExistsException;
import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.spi.CategoryPersistencePort;

import com.bootcamp.pragma.emazon.domain.validation.CategoryValidation;
import com.bootcamp.pragma.emazon.domain.validation.ValidateSortingTypeOfCategory;

import java.util.List;

public class CategoryUseCase implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {

        CategoryValidation.validateName(category);
        CategoryValidation.validateDescription(category);

        if (categoryPersistencePort.existsByName(category.getName())) {
            throw new CategoryNameAlreadyExistsException();
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategory(Integer page, Integer size, String sortingType) {
        ValidateSortingTypeOfCategory.validateSortingType(sortingType);
        return categoryPersistencePort.getAllCategory(page, size, sortingType );
    }
}
