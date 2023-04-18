package com.example.demo.controller;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Caracteristica;
import com.example.demo.service.CaracteristicaService;
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
@RequestMapping("/caracteristicas")
@Tag(name = "5. Feature Controller")
public class CaracteristicasController {
    private final CaracteristicaService service;

    @Operation(summary = "addFeature", security = {@SecurityRequirement(name = "jwt")})
    @PostMapping("/add")
    public ResponseEntity<String> Add(@RequestBody @Valid Caracteristica caracteristica){
        service.add(caracteristica);
        return ResponseEntity.status(HttpStatus.CREATED).body("Caracteristica: "+ caracteristica.getCaracteristicaNombre()+ ", with id: "+ caracteristica.getIdcaracteristica() + " added");
    }

    @Operation(summary = "featureList")
    @GetMapping
    public List<Caracteristica> getAll(){
        return service.List();
    }

    @Operation(summary = "deleteFeatureById", security = {@SecurityRequirement(name = "jwt")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)throws NotFoundException {
        service.deleteById(id);
        return ResponseEntity.ok("Feature con id: "+id + " eliminado");
    }
}
