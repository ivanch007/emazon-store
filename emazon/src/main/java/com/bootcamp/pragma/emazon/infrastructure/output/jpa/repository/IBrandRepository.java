package com.bootcamp.pragma.emazon.infrastructure.output.jpa.repository;

import com.bootcamp.pragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByName(String name);

    Page<BrandEntity> findAll(Pageable pageable);


}
