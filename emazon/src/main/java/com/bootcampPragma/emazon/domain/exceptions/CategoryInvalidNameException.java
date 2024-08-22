package com.bootcampPragma.emazon.domain.exceptions;

public class CategoryInvalidNameException extends IllegalArgumentException{
    public CategoryInvalidNameException(){
        super("El nombre es inválido");
    }
}
