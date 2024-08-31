package com.bootcamp.pragma.emazon.domain.spi;

import com.bootcamp.pragma.emazon.domain.model.Brand;

import java.util.List;

public interface BrandPersistencePort {

    void saveBrand(Brand brand);

    List<Brand> getAllBrands(Integer page, Integer size, String sortingType);

    boolean existsByName(String name);

    Long countTotalBrands();
}
