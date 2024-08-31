package com.bootcamp.pragma.emazon.domain.api;

import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;

import java.util.List;

public interface CategoryServicePort {

    void saveCategory(Category category);

    PagedResult<Category> getAllCategory(Integer page, Integer size, String sortingType);

}
