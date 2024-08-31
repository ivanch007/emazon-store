package com.bootcamp.pragma.emazon.domain.usecase;

import com.bootcamp.pragma.emazon.domain.api.BrandServicePort;
import com.bootcamp.pragma.emazon.domain.exceptions.brandexceptions.BrandNameAlreadyExistException;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import com.bootcamp.pragma.emazon.domain.spi.BrandPersistencePort;
import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;
import com.bootcamp.pragma.emazon.domain.validation.BrandValidation;
import com.bootcamp.pragma.emazon.domain.validation.ValidateSortingType;

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
    public PagedResult<Brand> getAllBrands(Integer page, Integer size, String sortingType) {
        ValidateSortingType.validateSortingType(sortingType);

        List<Brand> brands = brandPersistencePort.getAllBrands(page, size, sortingType);
        long totalElements = brandPersistencePort.countTotalBrands();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        boolean last = page == totalPages - 1;

        PagedResult<Brand> pagedResult = new PagedResult<>(brands, page, size, totalElements, totalPages, last);
        pagedResult.setContent(brands);
        pagedResult.setPage(page);
        pagedResult.setSize(size);
        pagedResult.setTotalElements(totalElements);
        pagedResult.setTotalPages(totalPages);
        pagedResult.setLast(page == totalPages - 1);

        return pagedResult;

    }
}
