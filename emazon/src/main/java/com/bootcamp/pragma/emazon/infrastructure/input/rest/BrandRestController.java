package com.bootcamp.pragma.emazon.infrastructure.input.rest;

import com.bootcamp.pragma.emazon.application.dto.BrandRequest;
import com.bootcamp.pragma.emazon.application.dto.BrandResponse;
import com.bootcamp.pragma.emazon.application.handler.IBrandHandler;
import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand/")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @PostMapping
    public ResponseEntity<Void> saveBrand(@RequestBody BrandRequest brandRequest){
        brandHandler.saveBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PagedResult<BrandResponse>> getAllBrands(@RequestParam(defaultValue = "0") Integer page,
                                                                        @RequestParam(defaultValue = "10") Integer size,
                                                                        @RequestParam(defaultValue = "asc") String sortingType){

        PagedResult<BrandResponse> response = brandHandler.getAllBrands(page, size, sortingType);
        return ResponseEntity.ok(response);
    }
}
