package com.example.demo.controller;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Imagen;
import com.example.demo.service.ImagenService;
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
@RequestMapping("/imagenes")
@Tag(name = "9. Image Controller")
public class ImagenController {
    private final ImagenService service;

    @Operation(summary = "addImage", security = {@SecurityRequirement(name = "jwt")})
    @PostMapping("/add")
    public ResponseEntity<String> Add(@RequestBody @Valid Imagen image){
        service.add(image);
        return ResponseEntity.status(HttpStatus.CREATED).body("Image: "+ image.getTitulo()+ ", with id: "+ image.getId() + " added");
    }
    @Operation(summary = "updateImage", security = {@SecurityRequirement(name = "jwt")})
    @PutMapping("/update")
    public ResponseEntity<String>update(@RequestBody @Valid Imagen imagen){
        service.update(imagen);
        return ResponseEntity.ok("Product: "+ imagen.getTitulo()+ ", with id: "+ imagen.getId() + " modified");
    }

    @Operation(summary = "imageList")
    @GetMapping
    public List<Imagen> getAll(){
        return service.List();
    }

    @Operation(summary = "deleteImageById", security = {@SecurityRequirement(name = "jwt")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)throws NotFoundException {
        service.deleteById(id);
        return ResponseEntity.ok("Image con id: "+id + " eliminado");
    }
}
