package com.bootcamp.pragma.emazon.infrastructure.output.jpa.adapter;

import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryNameAlreadyExistsException;
import com.bootcamp.pragma.emazon.domain.exceptions.NoDataFound;
import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.bootcamp.pragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Category> getAllCategory(Integer page, Integer size, String sortingType) {
        Pageable pagination = PageRequest.of(page, size, getSortingType(sortingType));
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll(pagination).getContent();
        if (categoryEntityList.isEmpty()) {
            throw new NoDataFound();
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }
    private Sort getSortingType(String sortingType){
        if (sortingType.equals("asc"))
            return Sort.by("name").ascending();

        return Sort.by("name").descending();
    }

    @Override
    public boolean existsByName(String name) {

        return false;

    }

    @Override
    public Long countTotalCategories() {
        return categoryRepository.count();
    }
}
