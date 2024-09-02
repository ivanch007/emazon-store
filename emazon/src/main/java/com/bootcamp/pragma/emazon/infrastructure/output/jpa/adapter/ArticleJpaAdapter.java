package com.bootcamp.pragma.emazon.infrastructure.output.jpa.adapter;

import com.bootcamp.pragma.emazon.domain.model.Article;
import com.bootcamp.pragma.emazon.domain.spi.ArticlePersistencePort;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements ArticlePersistencePort {

    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;


    @Override
    public void saveArticle(Article article) {

        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);
        articleRepository.save(articleEntity);

    }
}
