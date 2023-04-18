package com.example.demo.repository;


import com.example.demo.model.Puntuaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntuacionesRepository extends JpaRepository<Puntuaciones, Long>{
}
