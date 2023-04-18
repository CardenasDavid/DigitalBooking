package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name="categoria")

    public class Categoria {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idcategorias", nullable = false)
        private int id;
        @NotBlank
        @Size(min = 5, max = 45)
        private String titulo;
        @NotBlank
        @Size(min = 20, max = 150)
        private String descripcion;
        @Column(name = "url_imagen")
        @NotBlank
        @Size(min = 50, max = 200)
        private String urlImagen;

    }

