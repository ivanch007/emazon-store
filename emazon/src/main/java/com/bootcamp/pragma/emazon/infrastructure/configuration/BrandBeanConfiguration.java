package com.bootcamp.pragma.emazon.infrastructure.configuration;

import com.bootcamp.pragma.emazon.domain.api.BrandServicePort;
import com.bootcamp.pragma.emazon.domain.spi.BrandPersistencePort;
import com.bootcamp.pragma.emazon.domain.usecase.BrandUseCase;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BrandBeanConfiguration {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Bean
    public BrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public BrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }
}
