package com.example.demo.repository;

import com.example.demo.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservasRepository extends JpaRepository<Reservas, Long> {
    List<Reservas> findByClienteUsuarioId(Long usuarioId);
}
