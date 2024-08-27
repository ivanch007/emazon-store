package com.bootcamp.pragma.emazon.domain.api;

import com.bootcamp.pragma.emazon.domain.model.Category;

import java.util.List;

public interface CategoryServicePort {

    void saveCategory(Category category);

    List<Category> getAllCategory(Integer page, Integer size, String sortingType);

}
