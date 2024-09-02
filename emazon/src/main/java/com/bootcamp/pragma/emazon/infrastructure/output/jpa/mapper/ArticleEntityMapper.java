package com.bootcamp.pragma.emazon.infrastructure.output.jpa.mapper;

import com.bootcamp.pragma.emazon.domain.model.Article;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface ArticleEntityMapper {

    @Mapping(source = "categories", target = "categories")
    Article toArticle(ArticleEntity articleEntity);

    @Mapping(source = "categories", target = "categories")
    ArticleEntity toArticleEntity(Article article);
}
