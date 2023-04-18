package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2 , max=300)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Nombre")
    private String nombre;


    @Size(min=2 , max=300)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Apellido")
    private String apellido;


    @Size(min=2 , max=300)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Email")
    private String email;

    @Size(min=2 , max=400)
    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name="Contrasena")
    private String password;

    @Size(max=150)
    @Column(name="Ciudad")
    private String ciudad;


    @OneToMany(mappedBy ="id", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Puntuaciones> productos=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_x_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name="id_rol"))

    @NotNull
    private Set<Roles> roles = new HashSet<>();

    @OneToOne(mappedBy = "usuario")
    @JsonManagedReference
    private Cliente cliente;

    public Usuario(String nombre, String apellido, String ciudad, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.email = email;
        this.password = password;
    }
    private Usuario(){}
}

