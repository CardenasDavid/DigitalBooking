package com.example.demo.controller;



import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Ciudad;
import com.example.demo.service.CiudadService;
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
@RequestMapping("/ciudades")
@Tag(name = "4. City Controller")
public class CiudadController {


    private final CiudadService service;


    @Operation(summary = "addCiudad", security = {@SecurityRequirement(name = "jwt")})
    @PostMapping("/add")
    public ResponseEntity<String> Add(@RequestBody @Valid Ciudad ciudad){
        service.add(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body("City: "+ ciudad.getCiudad()+ ", with id: "+ ciudad.getId() + " added");
    }

    @Operation(summary = "ciudadesList")
    @GetMapping
    public List<Ciudad> getAll(){
        return service.List();
    }

        @Operation(summary = "updateCiudadById", security = {@SecurityRequirement(name = "jwt")})
        @PutMapping("/update/{id}")
        public ResponseEntity<String> updateById(@PathVariable long id, @RequestBody @Valid Ciudad ciudad) throws NotFoundException {
            service.updateById(id, ciudad);
            return ResponseEntity.ok("Ciudad: "+ ciudad.getCiudad()+ "with id: "+ id + " modified");
        }


    @Operation(summary = "deleteCityById", security = {@SecurityRequirement(name = "jwt")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)throws NotFoundException {
        service.deleteById(id);
        return ResponseEntity.ok("Ciudad con id: "+id + " eliminada");
    }

}
