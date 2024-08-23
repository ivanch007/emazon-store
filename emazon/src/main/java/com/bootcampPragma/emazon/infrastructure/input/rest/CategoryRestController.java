package com.bootcampPragma.emazon.infrastructure.input.rest;

import com.bootcampPragma.emazon.application.dto.CategoryRequest;
import com.bootcampPragma.emazon.application.dto.CategoryResponse;
import com.bootcampPragma.emazon.application.handler.ICategoryHandler;
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
    public ResponseEntity<List<CategoryResponse>> getCategories(){
        return ResponseEntity.ok(categoryHandler.getAllCategory());
    }

}
