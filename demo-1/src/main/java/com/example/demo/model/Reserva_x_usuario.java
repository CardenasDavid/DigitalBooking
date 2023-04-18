package com.example.demo.model;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class Reserva_x_usuario {

    @NotNull
    private LocalTime horaReserva;
    @NotNull
    private LocalDate fechaDesde;
    @NotNull
    private LocalDate fechaHasta;
    @NotNull
    private Integer idProducto;
    @NotNull
    private Long idUsuario;


}
