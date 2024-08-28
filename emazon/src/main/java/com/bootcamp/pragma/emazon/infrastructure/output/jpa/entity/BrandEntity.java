package com.bootcamp.pragma.emazon.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Size(max = 120)
    @Column(name = "description", nullable = false)
    private String description;
}
