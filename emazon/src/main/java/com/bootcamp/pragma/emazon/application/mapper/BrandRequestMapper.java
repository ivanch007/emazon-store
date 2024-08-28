package com.bootcamp.pragma.emazon.application.mapper;

import com.bootcamp.pragma.emazon.application.dto.BrandRequest;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface BrandRequestMapper {
    Brand toBrand(BrandRequest brandRequest);
}
