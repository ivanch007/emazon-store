package com.bootcamp.pragma.emazon.domain.exceptions;

public class InvalidParameter extends IllegalArgumentException{
    public InvalidParameter(){
        super("El parámetro sortingType debe ser 'asc' o 'desc'");
    }
}
