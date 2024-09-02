package com.bootcamp.pragma.emazon.infrastructure.output.jpa.repository;

import com.bootcamp.pragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
