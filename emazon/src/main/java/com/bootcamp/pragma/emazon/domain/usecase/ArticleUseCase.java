package com.bootcamp.pragma.emazon.domain.usecase;

import com.bootcamp.pragma.emazon.domain.api.ArticleServicePort;
import com.bootcamp.pragma.emazon.domain.model.Article;
import com.bootcamp.pragma.emazon.domain.spi.ArticlePersistencePort;
import com.bootcamp.pragma.emazon.domain.validation.ArticleValidation;

public class ArticleUseCase implements ArticleServicePort {

    private final ArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(ArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        ArticleValidation.validateArticle(article);
        articlePersistencePort.saveArticle(article);
    }
}
