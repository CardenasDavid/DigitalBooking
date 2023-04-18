package com.example.demo.repository;

import com.example.demo.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    Optional<Ciudad> findByCiudad(String ciudad);
}
