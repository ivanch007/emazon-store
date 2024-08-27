package com.bootcamp.pragma.emazon.domain.exceptions;

public class CategoryNameAlreadyExistsException extends IllegalArgumentException {
    public CategoryNameAlreadyExistsException() {
        super("El nombre de la categoría ya existe.");
    }
}
