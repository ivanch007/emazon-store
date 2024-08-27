package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.CategoryRequest;
import com.bootcamp.pragma.emazon.application.dto.CategoryResponse;
import com.bootcamp.pragma.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp.pragma.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp.pragma.emazon.domain.api.CategoryServicePort;
import com.bootcamp.pragma.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final CategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public List<CategoryResponse> getAllCategory(Integer page, Integer size, String sortingType) {

        List<CategoryResponse> responseList = categoryResponseMapper.toResponseList(
                categoryServicePort.getAllCategory(page, size, sortingType)
        );

        return responseList;
    }
}
