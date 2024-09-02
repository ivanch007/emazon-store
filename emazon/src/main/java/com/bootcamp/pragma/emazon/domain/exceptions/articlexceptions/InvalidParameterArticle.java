package com.bootcamp.pragma.emazon.domain.exceptions.articlexceptions;

public class InvalidParameterArticle extends IllegalArgumentException{
    public InvalidParameterArticle(){
        super("El artículo debe tener entre 1 y 3 categorías.");
    }
}
