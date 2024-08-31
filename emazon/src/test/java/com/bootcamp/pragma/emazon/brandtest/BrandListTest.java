package com.bootcamp.pragma.emazon.brandtest;

import com.bootcamp.pragma.emazon.domain.exceptions.InvalidParameter;
import com.bootcamp.pragma.emazon.domain.model.Brand;
import com.bootcamp.pragma.emazon.domain.spi.BrandPersistencePort;
import com.bootcamp.pragma.emazon.domain.usecase.BrandUseCase;

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

class BrandListTest {

    @Mock
    private BrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSortedAllBrandsAscending() {
        Brand brandOne = new Brand(1L, "Apple", "Technology company");
        Brand brandTwo = new Brand(2L, "Samsung", "Electronics manufacturer");
        List<Brand> brands = Arrays.asList(brandOne, brandTwo);

        when(brandPersistencePort.getAllBrands(0, 2, "asc")).thenReturn(brands);
        when(brandPersistencePort.countTotalBrands()).thenReturn(2L);

        PagedResult<Brand> result = brandUseCase.getAllBrands(0, 2, "asc");

        assertEquals(2, result.getContent().size());
        assertEquals("Apple", result.getContent().get(0).getName());
        assertEquals("Samsung", result.getContent().get(1).getName());
    }

    @Test
    void shouldReturnSortedAllBrandsDescending() {
        Brand brandOne = new Brand(1L, "Samsung", "Electronics manufacturer");
        Brand brandTwo = new Brand(2L, "Apple", "Technology company");
        List<Brand> brands = Arrays.asList(brandOne, brandTwo);

        when(brandPersistencePort.getAllBrands(0, 2, "desc")).thenReturn(brands);
        when(brandPersistencePort.countTotalBrands()).thenReturn(2L);

        PagedResult<Brand> result = brandUseCase.getAllBrands(0, 2, "desc");

        assertEquals(2, result.getContent().size());
        assertEquals("Samsung", result.getContent().get(0).getName());
        assertEquals("Apple", result.getContent().get(1).getName());
    }

    @Test
    void shouldReturnInvalidSortingType(){

        assertThrows(InvalidParameter.class, () -> {
            brandUseCase.getAllBrands(0, 2, "invalidSortType");
        });
    }

    @Test
    void shouldReturnCorrectPageForLargeResultSet() {
        // Arrange
        List<Brand> allBrands = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            allBrands.add(new Brand((long) i, "Brand " + i, "Description " + i));
        }

        when(brandPersistencePort.getAllBrands(0, 10, "asc"))
                .thenReturn(allBrands.subList(0, Math.min(10, allBrands.size())));
        when(brandPersistencePort.countTotalBrands()).thenReturn(50L);

        PagedResult<Brand> result = brandUseCase.getAllBrands(0, 10, "asc");
        List<Brand> resultList = result.getContent();

        assertEquals(10, resultList.size());
        assertEquals("Brand 0", resultList.get(0).getName());
        assertEquals("Brand 9", resultList.get(9).getName());
    }

    @Test
    void shouldReturnEmptyPageIfNoBrands() {
        when(brandPersistencePort.getAllBrands(1, 5, "asc"))
                .thenReturn(List.of());
        when(brandPersistencePort.countTotalBrands()).thenReturn(10L);

        PagedResult<Brand> result = brandUseCase.getAllBrands(1, 5, "asc");

        assertEquals(0, result.getContent().size());
        assertEquals(2, result.getTotalPages());
        assertEquals(10L, result.getTotalElements());
        assertTrue(result.isLast());
    }

    @Test
    void shouldReturnFullPageWhenExactNumberOfResults() {
        List<Brand> brands = Arrays.asList(
                new Brand(6L, "Brand6", "Description6"),
                new Brand(7L, "Brand7", "Description7"),
                new Brand(8L, "Brand8", "Description8"),
                new Brand(9L, "Brand9", "Description9"),
                new Brand(10L, "Brand10", "Description10")
        );

        when(brandPersistencePort.getAllBrands(1, 5, "asc"))
                .thenReturn(brands);
        when(brandPersistencePort.countTotalBrands()).thenReturn(10L);

        PagedResult<Brand> result = brandUseCase.getAllBrands(1, 5, "asc");

        assertEquals(5, result.getContent().size());
        assertEquals(2, result.getTotalPages());
        assertEquals(10L, result.getTotalElements());
        assertTrue(result.isLast());
    }

    @Test
    void shouldThrowExceptionForInvalidSortingType() {
        assertThrows(InvalidParameter.class, () -> {
            brandUseCase.getAllBrands(0, 2, "invalidSortType");
        });
    }

    @Test
    void shouldReturnFirstPage() {
        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Brand1", "Description1"),
                new Brand(2L, "Brand2", "Description2")
        );

        when(brandPersistencePort.getAllBrands(0, 2, "asc")).thenReturn(brands);
        when(brandPersistencePort.countTotalBrands()).thenReturn(4L);

        PagedResult<Brand> result = brandUseCase.getAllBrands(0, 2, "asc");

        assertEquals(2, result.getContent().size());
        assertEquals(2, result.getTotalPages());
        assertEquals(4L, result.getTotalElements());
        assertFalse(result.isLast());
    }
}
