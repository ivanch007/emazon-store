package com.bootcamp.pragma.emazon.application.mapper;

import com.bootcamp.pragma.emazon.application.dto.ArticleRequest;
import com.bootcamp.pragma.emazon.domain.model.Article;
import com.bootcamp.pragma.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleRequestMapper {

    @Mapping(target = "categories", source = "categoryId")
    Article toArticle(ArticleRequest articleRequest);

    default List<Category> mapCategoryIdsToCategories(List<Long> categoryIds) {
        if (categoryIds == null) {
            return Collections.emptyList();
        }
        return categoryIds.stream()
                .map(id -> new Category(id, null, null))
                .toList();
    }
}