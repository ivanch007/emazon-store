package com.bootcamp.pragma.emazon.domain.usecase;

import com.bootcamp.pragma.emazon.domain.api.CategoryServicePort;
import com.bootcamp.pragma.emazon.domain.exceptions.CategoryInvalidDescriptionException;
import com.bootcamp.pragma.emazon.domain.exceptions.CategoryInvalidNameException;
import com.bootcamp.pragma.emazon.domain.exceptions.CategoryNameAlreadyExistsException;
import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp.pragma.emazon.domain.validation.ValidateSortingTypeOfCategory;

import java.util.List;

public class CategoryUseCase implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getName() == null || category.getName().isBlank() || category.getName().length() > 50) {
            throw new CategoryInvalidNameException();

        }

        if (category.getDescription() == null || category.getDescription().isBlank() || category.getDescription().length() > 90) {
            throw new CategoryInvalidDescriptionException();
        }

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
