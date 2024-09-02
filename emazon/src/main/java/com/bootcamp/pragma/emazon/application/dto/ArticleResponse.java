package com.bootcamp.pragma.emazon.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleResponse {

    private String name;
    private String description;
    private int quantity;
    private double price;
    private List<Long> categoryId;
}
