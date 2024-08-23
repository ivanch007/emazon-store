package com.bootcampPragma.emazon.application.mapper;


import com.bootcampPragma.emazon.application.dto.CategoryRequest;
import com.bootcampPragma.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface CategoryRequestMapper {
    Category toCategory(CategoryRequest categoryRequest);

}
