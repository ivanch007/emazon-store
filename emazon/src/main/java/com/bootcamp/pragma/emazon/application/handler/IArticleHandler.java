package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.ArticleRequest;

public interface IArticleHandler {

    void saveArticle(ArticleRequest articleRequest);
}
