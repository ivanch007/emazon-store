package com.bootcamp.pragma.emazon.infrastructure.output.jpa.adapter;

import com.bootcamp.pragma.emazon.domain.exceptions.NoDataFound;
import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryNameAlreadyExistsException;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import com.bootcamp.pragma.emazon.domain.spi.BrandPersistencePort;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BrandJpaAdapter implements BrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new CategoryNameAlreadyExistsException();
        }
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public List<Brand> getAllBrands() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        if (brandEntityList.isEmpty()){
            throw new NoDataFound();
        }
        return brandEntityMapper.toBrandList(brandEntityList);
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
