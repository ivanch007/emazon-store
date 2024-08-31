package   com.bootcampPragma.emazon;

import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryInvalidDescriptionException;
import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryInvalidNameException;
import com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions.CategoryNameAlreadyExistsException;
import com.bootcamp.pragma.emazon.domain.model.Category;
import com.bootcamp.pragma.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp.pragma.emazon.domain.usecase.CategoryUseCase;
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

        Category categoryNullName = new Category(1L, null, "Valid description");
        assertThrows(CategoryInvalidNameException.class, () -> categoryUseCase.saveCategory(categoryNullName));


        Category categoryEmptyName = new Category(1L, "", "Valid description");
        assertThrows(CategoryInvalidNameException.class, () -> categoryUseCase.saveCategory(categoryEmptyName));


        // Arrange
        String longName = "A".repeat(91);
        Category categoryLongName = new Category(1L, longName, "Valid description");

        String longName2 = "A".repeat(1000);
        Category categoryLongName2 = new Category(1L, longName2, "Valid description");

        // Act & Assert
        assertThrows(CategoryInvalidNameException.class, () -> categoryUseCase.saveCategory(categoryLongName2));
        assertThrows(CategoryInvalidNameException.class, () -> categoryUseCase.saveCategory(categoryLongName));
    }

    @Test
    void testSaveCategory_InvalidDescription_ThrowsCategoryInvalidDescriptionException() {
        // Arrange
        Category category = new Category(1L, "Electronics", "");

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

}
