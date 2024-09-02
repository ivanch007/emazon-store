package com.bootcamp.pragma.emazon.domain.api;

import com.bootcamp.pragma.emazon.domain.model.Article;

public interface ArticleServicePort {
    void saveArticle(Article article);
}
