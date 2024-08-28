package com.bootcamp.pragma.emazon.application.handler;

import com.bootcamp.pragma.emazon.application.dto.BrandRequest;
import com.bootcamp.pragma.emazon.application.dto.BrandResponse;

import java.util.List;

public interface IBrandHandler {

    void saveCategory(BrandRequest brandRequest);
    List<BrandResponse> getAllCategories();
}
