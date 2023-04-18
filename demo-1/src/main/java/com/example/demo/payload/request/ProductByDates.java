package com.example.demo.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProductByDates {
    @NotNull
    private LocalDate fechaDesde;
    @NotNull
    private LocalDate fechaHasta;
}
