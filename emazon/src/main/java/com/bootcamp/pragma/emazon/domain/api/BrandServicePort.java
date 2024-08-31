package com.bootcamp.pragma.emazon.domain.api;

import com.bootcamp.pragma.emazon.domain.model.Brand;
import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;


public interface BrandServicePort {
    void saveBrand(Brand brand);

    PagedResult<Brand> getAllBrands(Integer page, Integer size, String sortingType);
}
