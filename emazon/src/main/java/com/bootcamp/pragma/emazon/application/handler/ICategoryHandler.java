package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.CategoryRequest;
import com.bootcamp.pragma.emazon.application.dto.CategoryResponse;
import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    PagedResult<CategoryResponse> getAllCategory(Integer page, Integer size, String sortingType);
}
