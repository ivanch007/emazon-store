package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.BrandRequest;
import com.bootcamp.pragma.emazon.application.dto.BrandResponse;
import com.bootcamp.pragma.emazon.application.mapper.BrandRequestMapper;
import com.bootcamp.pragma.emazon.application.mapper.BrandResponseMapper;
import com.bootcamp.pragma.emazon.domain.api.BrandServicePort;
import com.bootcamp.pragma.emazon.domain.model.Brand;

import java.util.List;

public class BrandHandler implements IBrandHandler{

    private final BrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;

    public BrandHandler(BrandServicePort brandServicePort, BrandRequestMapper brandRequestMapper, BrandResponseMapper brandResponseMapper) {
        this.brandServicePort = brandServicePort;
        this.brandRequestMapper = brandRequestMapper;
        this.brandResponseMapper = brandResponseMapper;
    }

    @Override
    public void saveCategory(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);

    }

    @Override
    public List<BrandResponse> getAllCategories() {

        return brandResponseMapper.toResponseList(brandServicePort.getAllBrands());
    }
}
