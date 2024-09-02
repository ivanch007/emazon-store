package com.bootcamp.pragma.emazon.domain.validation;

import com.bootcamp.pragma.emazon.domain.exceptions.InvalidParameter;
import com.bootcamp.pragma.emazon.domain.exceptions.articlexceptions.InvalidParameterArticle;
import com.bootcamp.pragma.emazon.domain.model.Article;
import com.bootcamp.pragma.emazon.domain.model.Category;

import java.util.HashSet;
import java.util.Set;

public class ArticleValidation {

    private ArticleValidation(){}

    public static void validateArticle(Article article) {
        if (article.getCategories() == null || article.getCategories().isEmpty() || article.getCategories().size() > 3) {
            throw new InvalidParameterArticle();
        }

        Set<Category> uniqueCategories = new HashSet<>(article.getCategories());
        if (uniqueCategories.size() != article.getCategories().size()) {
            throw new InvalidParameter("El artículo no puede tener categorías repetidas.");
        }

        if (article.getQuantity() < 0) {
            throw new InvalidParameter("La cantidad debe ser mayor o igual a 0.");
        }

        if (article.getPrice() <= 0) {
            throw new InvalidParameter("El precio debe ser mayor a 0.");
        }
    }
}

