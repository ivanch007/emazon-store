package com.bootcamp.pragma.emazon.domain.usecase;

import com.bootcamp.pragma.emazon.domain.api.CategoryServicePort;
import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryNameAlreadyExistsException;

import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.spi.CategoryPersistencePort;

import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;
import com.bootcamp.pragma.emazon.domain.validation.CategoryValidation;
import com.bootcamp.pragma.emazon.domain.validation.ValidateSortingType;

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
    public PagedResult<Category> getAllCategory(Integer page, Integer size, String sortingType) {
        ValidateSortingType.validateSortingType(sortingType);

        List<Category> categories = categoryPersistencePort.getAllCategory(page, size, sortingType);
        Long totalElements = categoryPersistencePort.countTotalCategories();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        boolean last = page == totalPages - 1;

        PagedResult<Category> pagedResult = new PagedResult<>(categories, page, size, totalElements, totalPages, last);
        pagedResult.setContent(categories);
        pagedResult.setPage(page);
        pagedResult.setSize(size);
        pagedResult.setTotalElements(totalElements);
        pagedResult.setTotalPages(totalPages);
        pagedResult.setLast(page == totalPages - 1);

        return pagedResult;

    }
}
