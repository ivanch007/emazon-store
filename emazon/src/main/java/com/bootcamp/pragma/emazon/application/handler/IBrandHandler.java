package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.BrandRequest;
import com.bootcamp.pragma.emazon.application.dto.BrandResponse;
import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;

public interface IBrandHandler {

    void saveBrand(BrandRequest brandRequest);
    PagedResult<BrandResponse> getAllBrands(Integer page, Integer size, String sortingType);
}
