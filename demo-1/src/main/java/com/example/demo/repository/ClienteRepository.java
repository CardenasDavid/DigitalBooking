package com.example.demo.repository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /*Optional<Cliente> findByUsuario(Usuario usuario);*/
// usuario es una relacion no es un atributo
    Boolean existsByUsuarioId(Long id);
    Cliente getIdByUsuarioId(Long id);
}
