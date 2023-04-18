package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "reservas")
@NoArgsConstructor
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reservas")
    private Long id;

    @Schema(type = "string",format = "HH:mm:ss")
    private LocalTime horaReserva;

    private LocalDate fechaDesde;

    private LocalDate fechaHasta;

   @ManyToOne
   @JoinColumn(name = "cliente_id")
   private Cliente cliente;

    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto")
    //@JsonBackReference(value = "reservasRef")
    @JsonIgnore
    Producto producto;
    @JsonProperty("producto")
    public Map<String, Object> getProductoId() {
        Map<String, Object> productoMap = new HashMap<>();
        productoMap.put("id", producto.getId());
        return productoMap;
    }


    public Reservas(LocalTime horaReserva, LocalDate fechaDesde, LocalDate fechaHasta, Producto producto, Cliente cliente) {

        this.horaReserva = horaReserva;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.cliente = cliente;
        this.producto = producto;

    }

    /*  @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;*/


}
