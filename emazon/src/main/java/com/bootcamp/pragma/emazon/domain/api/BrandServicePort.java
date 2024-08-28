package com.bootcamp.pragma.emazon.domain.api;

import com.bootcamp.pragma.emazon.domain.model.Brand;

import java.util.List;

public interface BrandServicePort {
    void saveBrand(Brand brand);

    List<Brand> getAllBrands();
}
