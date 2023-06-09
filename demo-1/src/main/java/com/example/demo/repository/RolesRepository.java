package com.example.demo.repository;

import com.example.demo.model.ERole;
import com.example.demo.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(ERole name);
}
