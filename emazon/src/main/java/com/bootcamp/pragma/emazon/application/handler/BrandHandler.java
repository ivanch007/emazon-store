package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.BrandRequest;
import com.bootcamp.pragma.emazon.application.dto.BrandResponse;
import com.bootcamp.pragma.emazon.application.mapper.BrandRequestMapper;
import com.bootcamp.pragma.emazon.application.mapper.BrandResponseMapper;
import com.bootcamp.pragma.emazon.domain.api.BrandServicePort;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final BrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);

    }

    @Override
    public PagedResult<BrandResponse> getAllBrands(Integer page, Integer size, String sortingType) {
        PagedResult<Brand> pagedResult = brandServicePort.getAllBrands(page, size, sortingType);
        List<BrandResponse> brandResponseList = brandResponseMapper.toResponseList(pagedResult.getContent());
        return new PagedResult<>(
                brandResponseList,
                pagedResult.getPage(),
                pagedResult.getSize(),
                pagedResult.getTotalElements(),
                pagedResult.getTotalPages(),
                pagedResult.isLast()
        );

    }
}
