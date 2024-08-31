package com.bootcamp.pragma.emazon.domain.spi;

import com.bootcamp.pragma.emazon.domain.model.Category;

import java.util.List;

public interface CategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> getAllCategory(Integer page, Integer size, String sortingType);

    boolean existsByName(String name);

    Long countTotalCategories();
}
