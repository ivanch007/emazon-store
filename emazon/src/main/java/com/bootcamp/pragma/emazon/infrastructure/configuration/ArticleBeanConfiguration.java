package com.bootcamp.pragma.emazon.infrastructure.configuration;

import com.bootcamp.pragma.emazon.domain.api.ArticleServicePort;
import com.bootcamp.pragma.emazon.domain.spi.ArticlePersistencePort;
import com.bootcamp.pragma.emazon.domain.usecase.ArticleUseCase;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.adapter.ArticleJpaAdapter;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ArticleBeanConfiguration {

    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Bean
    public ArticlePersistencePort articlePersistencePort(){
        return new ArticleJpaAdapter(articleRepository, articleEntityMapper);
    }

    @Bean
    public ArticleServicePort articleServicePort(){
        return new ArticleUseCase(articlePersistencePort());
    }
}
