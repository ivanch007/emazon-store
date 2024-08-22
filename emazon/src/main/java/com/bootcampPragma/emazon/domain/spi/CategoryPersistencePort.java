package com.bootcampPragma.emazon.domain.spi;

import com.bootcampPragma.emazon.domain.model.Category;

import java.util.List;

public interface CategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> getAllCategory();

    boolean existsByName(String name);
}
