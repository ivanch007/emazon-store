package com.bootcamp.pragma.emazon.application.mapper;

import com.bootcamp.pragma.emazon.application.dto.ArticleResponse;
import com.bootcamp.pragma.emazon.domain.model.Article;
import com.bootcamp.pragma.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleResponseMapper {

    @Mapping(target = "categoryId", source = "categories")
    ArticleResponse toArticleResponse(Article article);

    default List<Long> mapCategoriesToCategoryIds(List<Category> categories) {
        if (categories == null) {
            return Collections.emptyList();
        }
        return categories.stream()
                .map(Category::getId)
                .toList();
    }
}