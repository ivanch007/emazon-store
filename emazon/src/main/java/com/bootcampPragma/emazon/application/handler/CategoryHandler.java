package com.bootcampPragma.emazon.application.handler;

import com.bootcampPragma.emazon.application.dto.CategoryRequest;
import com.bootcampPragma.emazon.application.dto.CategoryResponse;
import com.bootcampPragma.emazon.application.mapper.CategoryRequestMapper;
import com.bootcampPragma.emazon.application.mapper.CategoryResponseMapper;
import com.bootcampPragma.emazon.domain.api.CategoryServicePort;
import com.bootcampPragma.emazon.domain.model.Category;
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
    public List<CategoryResponse> getAllCategory() {

        List<CategoryResponse> responseList = categoryResponseMapper.toResponseList(categoryServicePort.getAllCategory());

        //categoryPaginationUtil.paginate(responseList);

        return responseList;
    }
}
