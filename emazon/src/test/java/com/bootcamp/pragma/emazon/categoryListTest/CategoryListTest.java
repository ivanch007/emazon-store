package com.bootcamp.pragma.emazon.categoryListTest;

import com.bootcamp.pragma.emazon.domain.exceptions.InvalidParameter;
import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp.pragma.emazon.domain.usecase.CategoryUseCase;

import com.bootcamp.pragma.emazon.domain.util.pagination.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void shouldReturnSortedAllCategoriesAscending() {
        Category categoryOne = new Category(1L, "Computer", "This is a description");
        Category categoryTwo = new Category(2L, "Mouse", "you can do click");
        List<Category> categories = Arrays.asList(categoryOne, categoryTwo);

        when(categoryPersistencePort.getAllCategory(0, 2, "asc")).thenReturn(categories);
        when(categoryPersistencePort.countTotalCategories()).thenReturn(2L);

        PagedResult<Category> result = categoryUseCase.getAllCategory(0, 2, "asc");

        assertEquals(2, result.getContent().size());
        assertEquals("Computer", result.getContent().get(0).getName());
        assertEquals("Mouse", result.getContent().get(1).getName());
    }

    @Test
    void shouldReturnSortedAllCategoriesDescending() {
        Category categoryOne = new Category(1L, "Mouse", "This is a description");
        Category categoryTwo = new Category(2L, "Computer", "you can do click");
        List<Category> categories = Arrays.asList(categoryOne, categoryTwo);

        when(categoryPersistencePort.getAllCategory(0, 2, "desc")).thenReturn(categories);
        when(categoryPersistencePort.countTotalCategories()).thenReturn(2L);

        PagedResult<Category> result = categoryUseCase.getAllCategory(0, 2, "desc");

        assertEquals(2, result.getContent().size());
        assertEquals("Mouse", result.getContent().get(0).getName());
        assertEquals("Computer", result.getContent().get(1).getName());
    }
    @Test
    void shouldReturnInvalidSortingType(){

        assertThrows(InvalidParameter.class, () -> {
            categoryUseCase.getAllCategory(0, 2, "invalidSortType");
        });
    }

    @Test
    void shouldReturnCorrectPageForLargeResultSet() {
        // Arrange
        List<Category> allCategories = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            allCategories.add(new Category((long) i, "Category " + i, "Description " + i));
        }

        when(categoryPersistencePort.getAllCategory(0, 10, "asc"))
                .thenReturn(allCategories.subList(0, Math.min(10, allCategories.size())));
        when(categoryPersistencePort.countTotalCategories()).thenReturn(50L);

        PagedResult<Category> result = categoryUseCase.getAllCategory(0, 10, "asc");
        List<Category> resultList = result.getContent();

        assertEquals(10, resultList.size());
        assertEquals("Category 0", resultList.get(0).getName());
        assertEquals("Category 9", resultList.get(9).getName());
    }


    @Test
    void shouldReturnEmptyPageIfNoCategories() {
        when(categoryPersistencePort.getAllCategory(1, 5, "asc"))
                .thenReturn(List.of());
        when(categoryPersistencePort.countTotalCategories()).thenReturn(10L);

        PagedResult<Category> result = categoryUseCase.getAllCategory(1, 5, "asc");

        assertEquals(0, result.getContent().size());
        assertEquals(2, result.getTotalPages());
        assertEquals(10L, result.getTotalElements());
        assertTrue(result.isLast());
    }

    @Test
    void shouldReturnFullPageWhenExactNumberOfResults() {
        List<Category> categories = Arrays.asList(
                new Category(6L, "Category6", "Description6"),
                new Category(7L, "Category7", "Description7"),
                new Category(8L, "Category8", "Description8"),
                new Category(9L, "Category9", "Description9"),
                new Category(10L, "Category10", "Description10")
        );

        when(categoryPersistencePort.getAllCategory(1, 5, "asc"))
                .thenReturn(categories);
        when(categoryPersistencePort.countTotalCategories()).thenReturn(10L);

        PagedResult<Category> result = categoryUseCase.getAllCategory(1, 5, "asc");

        assertEquals(5, result.getContent().size());
        assertEquals(2, result.getTotalPages());
        assertEquals(10L, result.getTotalElements());
        assertTrue(result.isLast());
    }

    @Test
    void shouldThrowExceptionForInvalidSortingType() {
        assertThrows(InvalidParameter.class, () -> {
            categoryUseCase.getAllCategory(0, 2, "invalidSortType");
        });
    }

    @Test
    void shouldReturnFirstPage() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category1", "Description1"),
                new Category(2L, "Category2", "Description2")
        );

        when(categoryPersistencePort.getAllCategory(0, 2, "asc")).thenReturn(categories);
        when(categoryPersistencePort.countTotalCategories()).thenReturn(4L);

        PagedResult<Category> result = categoryUseCase.getAllCategory(0, 2, "asc");

        assertEquals(2, result.getContent().size());
        assertEquals(2, result.getTotalPages());
        assertEquals(4L, result.getTotalElements());
        assertFalse(result.isLast());
    }
}
