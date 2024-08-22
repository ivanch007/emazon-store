package com.bootcampPragma.emazon.domain.exceptions;

public class CategoryInvalidDescriptionException extends IllegalArgumentException{
    public CategoryInvalidDescriptionException(){
        super("La descripcion es invalida");
    }
}
