package com.bootcampPragma.emazon.application.mapper;

import com.bootcampPragma.emazon.application.dto.CategoryResponse;
import com.bootcampPragma.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface CategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);

     List<CategoryResponse> toResponseList(List<Category> categoryList);

}
