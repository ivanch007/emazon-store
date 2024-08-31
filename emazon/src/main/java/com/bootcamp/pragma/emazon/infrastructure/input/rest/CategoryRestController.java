package com.bootcamp.pragma.emazon.infrastructure.input.rest;


import com.bootcamp.pragma.emazon.application.dto.CategoryRequest;
import com.bootcamp.pragma.emazon.application.dto.CategoryResponse;
import com.bootcamp.pragma.emazon.application.handler.ICategoryHandler;
import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryRestController {
    private final ICategoryHandler categoryHandler;

    @PostMapping
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryRequest categoryRequest){
        categoryHandler.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PagedResult<CategoryResponse>> getAllCategories(@RequestParam(defaultValue = "0") Integer page,
                                                                       @RequestParam(defaultValue = "10") Integer size,
                                                                       @RequestParam(defaultValue = "asc") String sortingType){

        PagedResult<CategoryResponse> response = categoryHandler.getAllCategory(page, size, sortingType);
        return ResponseEntity.ok(response);
    }

}
