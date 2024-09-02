package com.bootcamp.pragma.emazon.infrastructure.input.rest;

import com.bootcamp.pragma.emazon.application.dto.ArticleRequest;
import com.bootcamp.pragma.emazon.application.handler.IArticleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/")
@RequiredArgsConstructor
public class ArticleRestController {

    private final IArticleHandler articleHandler;

    @PostMapping
    ResponseEntity<Void> saveArticle(@RequestBody ArticleRequest articleRequest){
        articleHandler.saveArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
