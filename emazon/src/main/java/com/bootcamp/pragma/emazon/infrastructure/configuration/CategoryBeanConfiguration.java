package com.bootcamp.pragma.emazon.infrastructure.configuration;

import com.bootcamp.pragma.emazon.domain.api.CategoryServicePort;
import com.bootcamp.pragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp.pragma.emazon.domain.usecase.CategoryUseCase;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryBeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public CategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }
}
