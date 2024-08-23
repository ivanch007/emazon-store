package com.bootcampPragma.emazon.infrastructure.output.jpa.adapter;

import com.bootcampPragma.emazon.domain.exceptions.CategoryNameAlreadyExistsException;
import com.bootcampPragma.emazon.domain.exceptions.NoDataFound;
import com.bootcampPragma.emazon.domain.model.Category;
import com.bootcampPragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcampPragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import com.bootcampPragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.bootcampPragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryNameAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }


    @Override
    public List<Category> getAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()){
            throw new NoDataFound();
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
