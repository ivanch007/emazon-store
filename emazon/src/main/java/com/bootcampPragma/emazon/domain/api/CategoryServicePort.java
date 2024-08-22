package com.bootcampPragma.emazon.domain.api;

import com.bootcampPragma.emazon.domain.model.Category;

import java.util.List;

public interface CategoryServicePort {

    void saveCategory(Category category);

    List<Category> getAllCategory();

}
