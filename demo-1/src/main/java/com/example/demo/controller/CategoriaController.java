package com.example.demo.controller;


import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.model.Categoria;
import com.example.demo.service.CategoriaService;
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
@RequestMapping("/categorias")
@Tag(name = "8. Category Controller")
public class CategoriaController {
    private final CategoriaService service;

    @Operation(summary = "addCategory", security = {@SecurityRequirement(name = "jwt")})
    @PostMapping("/add")
    public ResponseEntity<String> Add(@RequestBody @Valid Categoria categoria){
        service.add(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria: "+ categoria.getTitulo()+ ", with id: "+ categoria.getId() + " added");
    }
    @Operation(summary = "categoryList")
    @GetMapping
    public List<Categoria> getAll(){
        return service.List();
    }

    @Operation(summary = "updateCategoryById", security = {@SecurityRequirement(name = "jwt")})
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateById(@PathVariable int id, @RequestBody @Valid Categoria categoria) throws CategoryNotFoundException{
        service.updateById(id, categoria);
        return ResponseEntity.ok("Category: "+ categoria.getTitulo()+ "with id: "+ id + " modified");
    }

    @Operation(summary = "deleteCategoryById", security = {@SecurityRequirement(name = "jwt")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)throws CategoryNotFoundException{
        service.deleteById(id);
        return ResponseEntity.ok("Categoria con id: "+id + " eliminado");
    }


}
