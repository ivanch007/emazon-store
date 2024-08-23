package com.bootcampPragma.emazon.application.handler;

import com.bootcampPragma.emazon.application.dto.CategoryRequest;
import com.bootcampPragma.emazon.application.dto.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategory();
}
