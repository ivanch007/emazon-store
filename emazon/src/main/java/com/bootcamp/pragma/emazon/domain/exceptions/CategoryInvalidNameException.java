package com.bootcamp.pragma.emazon.domain.exceptions;

public class CategoryInvalidNameException extends IllegalArgumentException{
    public CategoryInvalidNameException(){
        super("El nombre de la categoría es inválido: debe tener entre 1 y 50 caracteres.");
    }
}
