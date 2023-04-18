package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long id;

    @Size(min=2 , max=500)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Titulo")
    private String titulo;

    @Size(min=2 , max=200)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Ubicacion")
    private String ubicacion;

    @Size(min=2 , max=1000)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Descripcion")
    private String descripcion;

    @Size(min=2 , max=150)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Direccion")
    private String direccion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_Categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_Ciudad", nullable = false)
    private Ciudad ciudad;


    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "imagenRef")
    @JoinColumn(name = "id_producto")
    private Set<Imagen> imagenes=new HashSet<>();


    @ManyToMany
    @JoinTable(name = "productos_x_caracteristicas",
            joinColumns = @JoinColumn(name = "id_productos"),
            inverseJoinColumns = @JoinColumn(name="id_caracteristica"))
    private Set<Caracteristica> caracteristicas= new HashSet<>();

        //POLITICS
    @Size(max=350)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Normas_Casa")
    private String normasCasa;
    @Size(max=350)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Medidas_Salud_Seguridad")
    private String medidasSaludSeguridad;
    @Size(max=350)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Politicas_Cancelacion")
    private String politicasCancelacion;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    //@JsonManagedReference(value = "reservasRef")
    @JsonIgnoreProperties({"producto"})
    private Set<Reservas> reservas=new HashSet<>();
}
