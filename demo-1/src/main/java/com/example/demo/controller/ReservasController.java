package com.example.demo.controller;


import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Reserva_x_usuario;
import com.example.demo.model.Reservas;
import com.example.demo.service.ReservasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/reservas")
@Tag(name = "3. Reservas Controller")
@SecurityRequirement(name = "jwt")
public class ReservasController {

    private final ReservasService service;

    @Operation(summary = "addReservas")
    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody @Valid Reserva_x_usuario reserva_x_usuario) throws Exception {
        service.add(reserva_x_usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reserva added y cliente creado del usuario:" + reserva_x_usuario.getIdUsuario());
    }
    @Operation(summary = "updateReservas")
    @PutMapping("/update")
    public ResponseEntity<String>update(@RequestBody @Valid Reservas reserva){
        service.update(reserva);
        return ResponseEntity.ok("Reserva: "+ reserva.getId() + " modified");
    }
    @Operation(summary = "updateReservasById")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody @Valid Reservas reserva) throws NotFoundException {
        service.update(reserva);
        return ResponseEntity.ok("Reserva: "+ reserva.getId()+ " modified");
    }

    @Operation(summary = "ReservasList")
    @GetMapping
    public List<Reservas> getAll(){
        return service.list();
    }

    @Operation(summary = "getReservasyById")
    @GetMapping("/{id}")
    public Reservas getById(@PathVariable Long id){
        return service.getById(id);
    }

    @Operation(summary = "deleteReservasById")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)throws NotFoundException {
        service.deleteById(id);
        return ResponseEntity.ok("Reservas con id: "+id + " eliminado");
    }

    @Operation(summary = "getReservasByUsuarioId")
    @GetMapping("/user/{id}")
    public List<Reservas> getReservasByUsuarioId (@PathVariable Long id){
        return service.listByUsuarioId(id);
    }

}

