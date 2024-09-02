package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.ArticleRequest;
import com.bootcamp.pragma.emazon.application.mapper.ArticleRequestMapper;
import com.bootcamp.pragma.emazon.application.mapper.ArticleResponseMapper;
import com.bootcamp.pragma.emazon.domain.api.ArticleServicePort;
import com.bootcamp.pragma.emazon.domain.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler{

    private final ArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;
    private final ArticleResponseMapper articleResponseMapper;

    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);

    }
}
