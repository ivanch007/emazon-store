package com.bootcamp.pragma.emazon.categoryListTest;

import com.bootcamp.pragma.emazon.domain.exceptions.InvalidParameter;
import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp.pragma.emazon.domain.usecase.CategoryUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CategoryListTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSortedAllCategoriesAscending(){

        Category categoryOne = new Category(1L, "Computer", "This is a description");
        Category categoryTwo = new Category(2L, "Mouse", "you can do click");
        List<Category> categories = Arrays.asList(categoryOne, categoryTwo);

        when(categoryPersistencePort.getAllCategory(0,2,"asc")).thenReturn(categories);

        List<Category> result = categoryUseCase.getAllCategory(0,2, "asc");

        assertEquals(2,result.size());
        assertEquals("Computer", result.get(0).getName());
        assertEquals("Mouse", result.get(1).getName());

    }

    @Test
    void shouldReturnSortedAllCategoriesDescending(){

        Category categoryOne = new Category(1L, "Mouse", "This is a description");
        Category categoryTwo = new Category(2L, "Computer", "you can do click");
        List<Category> categories = Arrays.asList(categoryOne, categoryTwo);

        when(categoryPersistencePort.getAllCategory(0,2, "desc")).thenReturn(categories);

        List<Category> result = categoryUseCase.getAllCategory(0,2, "desc");

        assertEquals(2, result.size());
        assertEquals("Mouse", result.get(0).getName());
        assertEquals("Computer", result.get(1).getName());
    }
    @Test
    void shouldReturnInvalidSortingType(){

        assertThrows(InvalidParameter.class, () -> {
            categoryUseCase.getAllCategory(0, 2, "invalidSortType");
        });
    }


}
