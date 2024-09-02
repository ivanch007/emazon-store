package com.bootcamp.pragma.emazon.domain.spi;

import com.bootcamp.pragma.emazon.domain.model.Article;

public interface ArticlePersistencePort {
    void saveArticle(Article article);
}
