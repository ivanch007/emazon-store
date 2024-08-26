package com.bootcampPragma.emazon;

import com.bootcampPragma.emazon.domain.exceptions.CategoryInvalidDescriptionException;
import com.bootcampPragma.emazon.domain.exceptions.CategoryInvalidNameException;
import com.bootcampPragma.emazon.domain.exceptions.CategoryNameAlreadyExistsException;
import com.bootcampPragma.emazon.domain.model.Category;
import com.bootcampPragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcampPragma.emazon.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveCategory(){
        // Arrange
        Category category = new Category(1L, "Electronics", "All kinds of electronic devices");
        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(false);

        //Act
        categoryUseCase.saveCategory(category);

        //Assert
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void shouldThrowExceptionWithNameNull() {
        // Nombre nulo
        Category categoryNullName = new Category(1L, null, "Valid description");
        assertThrows(CategoryInvalidNameException.class, () -> categoryUseCase.saveCategory(categoryNullName));

        // Nombre vacío
        Category categoryEmptyName = new Category(1L, "", "Valid description");
        assertThrows(CategoryInvalidNameException.class, () -> categoryUseCase.saveCategory(categoryEmptyName));

        // Nombre demasiado largo
        // Arrange
        String longName = "A".repeat(91); // Nombre con 51 caracteres
        Category categoryLongName = new Category(1L, longName, "Valid description");

        String longName2 = "A".repeat(1000); // Nombre con 51 caracteres
        Category categoryLongName2 = new Category(1L, longName2, "Valid description");

        // Act & Assert
        assertThrows(CategoryInvalidNameException.class, () -> categoryUseCase.saveCategory(categoryLongName2));
        assertThrows(CategoryInvalidNameException.class, () -> categoryUseCase.saveCategory(categoryLongName));
    }

    @Test
    void testSaveCategory_InvalidDescription_ThrowsCategoryInvalidDescriptionException() {
        // Arrange
        Category category = new Category(1L, "Electronics", ""); // Descripción inválida

        // Act & Assert
        assertThrows(CategoryInvalidDescriptionException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void testSaveCategory_NameAlreadyExists_ThrowsCategoryNameAlreadyExistsException() {
        // Arrange
        Category category = new Category(1L, "Electronics", "All kinds of electronic devices");

        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(CategoryNameAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
    }



    @Test
    void testGetAllCategory_ReturnsCategoryList() {
        // Arrange
        List<Category> categories = List.of(
                new Category(1L, "Electronics", "All kinds of electronic devices"),
                new Category(2L, "Books", "Various kinds of books")
        );
        when(categoryPersistencePort.getAllCategory()).thenReturn(categories);

        // Act
        List<Category> result = categoryUseCase.getAllCategory();

        // Assert
        verify(categoryPersistencePort, times(1)).getAllCategory();
        assertEquals(categories, result);
    }

}
