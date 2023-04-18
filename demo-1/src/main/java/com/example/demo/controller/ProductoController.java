package com.example.demo.controller;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/productos")
@Tag(name = "2. Product Controller")
public class ProductoController {
    private final ProductoService service;
    @Operation(summary = "addProduct", security = {@SecurityRequirement(name = "jwt")})
    @PostMapping("/add")
    public ResponseEntity<Producto>save(@RequestBody @Valid Producto product){
        Producto newProduct = service.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
    @Operation(summary = "updateProduct", security = {@SecurityRequirement(name = "jwt")})
    @PutMapping("/update")
    public ResponseEntity<String>update(@RequestBody @Valid Producto product){
        service.update(product);
        return ResponseEntity.ok("Product: "+ product.getTitulo()+ ", with id: "+ product.getId() + " modified");
    }
    @Operation(summary = "updateProductById", security = {@SecurityRequirement(name = "jwt")})
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateById(@PathVariable int id, @RequestBody @Valid Producto product) throws NotFoundException {
        service.updateById(id, product);
        return ResponseEntity.ok("Product: "+ product.getTitulo()+ ", with id: "+ id + " modified");
    }

    @Operation(summary = "productList")
    @GetMapping
    public List<Producto> getAll(){
        return service.List();
    }

    @Operation(summary = "getProductyById")
    @GetMapping("/{id}")
    public Producto getById(@PathVariable int id){
        return service.getById(id);
    }

    @Operation(summary = "deleteProductById", security = {@SecurityRequirement(name = "jwt")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)throws NotFoundException {
        service.deleteById(id);
        return ResponseEntity.ok("Producto con id: "+id + " eliminado");
    }

    @Operation(summary = "productListByCity")
    @GetMapping("/ciudad/{city}")
    public List<Producto> getAllByCity(@PathVariable String city){
        return service.findProductByCity(city);
    }
    @Operation(summary = "productListByCategory")
    @GetMapping("/category/{title}")
    public List<Producto> getAllByCategory(@PathVariable String title){
        return service.findProductByCategory(title);
    }

    @Operation(summary = "productListByTwoGivenDates")
    @GetMapping("/{fechaDesde}/{fechaHasta}")
    public List<Producto> listByDates(@PathVariable LocalDate fechaDesde,@PathVariable LocalDate fechaHasta){
        return service.listByDates(fechaDesde,fechaHasta);
    }
    @Operation(summary = "productListByTwoGivenDatesAndCity")
    @GetMapping("/ciudad/{city}/{fechaDesde}/{fechaHasta}")
    public List<Producto> listByDatesAndCitybyparam(@PathVariable String city, @PathVariable LocalDate fechaDesde,@PathVariable LocalDate fechaHasta){
        return service.listByDatesAndCity(city,fechaDesde,fechaHasta);
    }
}
