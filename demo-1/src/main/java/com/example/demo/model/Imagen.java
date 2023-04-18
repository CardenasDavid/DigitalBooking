package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "imagen")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2 , max=50)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Titulo")
    private String titulo;

    @Size(min=2 , max=300)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="URL")
    private String URL;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    @JsonBackReference(value = "imagenRef")
    private Producto producto;

}
