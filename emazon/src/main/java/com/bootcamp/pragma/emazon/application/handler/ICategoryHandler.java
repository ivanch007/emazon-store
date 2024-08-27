package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.CategoryRequest;
import com.bootcamp.pragma.emazon.application.dto.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategory(Integer page, Integer size, String sortingType);
}
