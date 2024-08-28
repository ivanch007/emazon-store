package com.bootcamp.pragma.emazon.domain.exceptions.categoryexceptions;

public class CategoryNameAlreadyExistsException extends IllegalArgumentException {
    public CategoryNameAlreadyExistsException() {
        super("El nombre de la ya existe.");
    }
}
