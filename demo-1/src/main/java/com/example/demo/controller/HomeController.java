package com.example.demo.controller;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/home")
@Tag(name = "7. Home Controller", description = "access to get 8 random products")
public class HomeController {
    public final ProductoRepository repository;
    @Operation(summary = "get-8-RandomProducts")
    @GetMapping
    public List<Producto> RandomProducts() throws BadRequestException{
        if (repository.count() < 8) {
            throw new BadRequestException("No hay suficientes productos en la Base de Datos");
        }
        var fullListProducts= repository.findAll();
        Collections.shuffle(fullListProducts);
        return fullListProducts.subList(0, 8);
    }
}
