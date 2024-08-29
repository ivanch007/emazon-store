package com.bootcamp.pragma.emazon.brandtest;

import com.bootcamp.pragma.emazon.domain.exceptions.brandexceptions.BrandInvalidDescriptionException;
import com.bootcamp.pragma.emazon.domain.exceptions.brandexceptions.BrandInvalidNameException;
import com.bootcamp.pragma.emazon.domain.exceptions.brandexceptions.BrandNameAlreadyExistException;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import com.bootcamp.pragma.emazon.domain.spi.BrandPersistencePort;
import com.bootcamp.pragma.emazon.domain.usecase.BrandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateBrandTest {

    @Mock
    private BrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;
    private Brand brand;

    @BeforeEach
    public void  setUp(){
        brand = new Brand(1L, "Nike", "Sportswear");
    }

    @Test
     void shouldSaveBrandSuccesfully(){

        when(brandPersistencePort.existsByName(brand.getName())).thenReturn(false);

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExist(){

        when(brandPersistencePort.existsByName(brand.getName())).thenReturn(true);

        assertThrows(BrandNameAlreadyExistException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        verify(brandPersistencePort, never()).saveBrand(brand);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull(){

        Brand brandNullName = new Brand(1L, null, "it doesn't have name but it has description");

        assertThrows(BrandInvalidNameException.class, () -> brandUseCase.saveBrand(brandNullName));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty(){
        Brand brandEmptyName = new Brand(1L, "", "This is a description");

        assertThrows(BrandInvalidNameException.class, () -> brandUseCase.saveBrand(brandEmptyName));
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooLong(){
        String longName = "A".repeat(55);
        Brand brandLongName = new Brand(1l, longName, "A description again");

        assertThrows(BrandInvalidNameException.class, () -> brandUseCase.saveBrand(brandLongName));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty(){

        Brand brandEmptyDescription = new Brand(1L, "I am a name", "");

        assertThrows(BrandInvalidDescriptionException.class, () -> brandUseCase.saveBrand(brandEmptyDescription));

    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull(){
        Brand brandNullDescription = new Brand(1L, "I am a name", null);

        assertThrows(BrandInvalidDescriptionException.class, () -> brandUseCase.saveBrand(brandNullDescription));
    }



}
