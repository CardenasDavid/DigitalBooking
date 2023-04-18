package com.example.demo.controller;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Imagen;
import com.example.demo.model.Puntuaciones;
import com.example.demo.service.ImagenService;
import com.example.demo.service.PuntuacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/puntuaciones")
@Tag(name = "6. Rating Controller", description = "access to rate products")
public class PuntuacionController {
    private final PuntuacionService service;

    @Operation(summary = "addProductRating")
    @PostMapping("/add")
    public ResponseEntity<String> Add(@RequestBody @Valid Puntuaciones puntuaciones){
        service.add(puntuaciones);
        return ResponseEntity.ok(puntuaciones + "agregado");
    }

    @Operation(summary = "productRatingList")
    @GetMapping
    public List<Puntuaciones> getAll(){
        return service.List();
    }

    @Operation(summary = "deleteproductRatingById")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)throws NotFoundException {
        service.deleteById(id);
        return ResponseEntity.ok("Product Rating con id: "+id + " eliminado");
    }
}
