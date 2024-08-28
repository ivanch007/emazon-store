package com.bootcamp.pragma.emazon.application.mapper;

import com.bootcamp.pragma.emazon.application.dto.BrandResponse;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface BrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);

    List<BrandResponse> toResponseList(List<Brand> brandList);
}
