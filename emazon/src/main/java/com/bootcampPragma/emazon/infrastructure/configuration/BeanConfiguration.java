package com.bootcampPragma.emazon.infrastructure.configuration;

import com.bootcampPragma.emazon.domain.api.CategoryServicePort;
import com.bootcampPragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcampPragma.emazon.domain.usecase.CategoryUseCase;
import com.bootcampPragma.emazon.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.bootcampPragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.bootcampPragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

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
