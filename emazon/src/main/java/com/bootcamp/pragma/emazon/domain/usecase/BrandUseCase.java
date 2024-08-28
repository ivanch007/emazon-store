package com.bootcamp.pragma.emazon.domain.usecase;

import com.bootcamp.pragma.emazon.domain.api.BrandServicePort;
import com.bootcamp.pragma.emazon.domain.exceptions.brandexceptions.BrandNameAlreadyExistException;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import com.bootcamp.pragma.emazon.domain.spi.BrandPersistencePort;
import com.bootcamp.pragma.emazon.domain.validation.BrandValidation;

import java.util.List;

public class BrandUseCase implements BrandServicePort {

    private final BrandPersistencePort brandPersistencePort;

    public BrandUseCase(BrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }


    @Override
    public void saveBrand(Brand brand) {
        BrandValidation.validateName(brand);
        BrandValidation.validateDescription(brand);

        if (brandPersistencePort.existsByName(brand.getName())) {
            throw new BrandNameAlreadyExistException();
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return List.of();
    }
}
