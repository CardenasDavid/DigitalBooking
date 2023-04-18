package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    @Query(value = "SELECT * FROM producto WHERE id_producto NOT IN (SELECT reservas.id_producto FROM reservas WHERE fecha_desde BETWEEN :startDate AND :endDate \n" +
            "or fecha_hasta BETWEEN :startDate AND :endDate)", nativeQuery = true)
    List<Producto> listByDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Producto> findByCiudadCiudad(String city);

    List<Producto> findByCategoriaTitulo(String title);
}
