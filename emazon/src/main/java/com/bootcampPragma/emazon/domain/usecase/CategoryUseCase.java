package com.bootcampPragma.emazon.domain.usecase;

import com.bootcampPragma.emazon.domain.api.CategoryServicePort;
import com.bootcampPragma.emazon.domain.exceptions.CategoryInvalidDescriptionException;
import com.bootcampPragma.emazon.domain.exceptions.CategoryInvalidNameException;
import com.bootcampPragma.emazon.domain.exceptions.CategoryNameAlreadyExistsException;
import com.bootcampPragma.emazon.domain.model.Category;
import com.bootcampPragma.emazon.domain.spi.CategoryPersistencePort;

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
    public List<Category> getAllCategory() {
        return categoryPersistencePort.getAllCategory();
    }
}
