package com.example.demo.controller;


import com.example.demo.model.Ciudad;
import com.example.demo.model.Roles;
import com.example.demo.service.RolesService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/roles")
public class RolesController {
    private final RolesService service;

    @Operation(summary = "RolesList")
    @GetMapping
    public List<Roles> getAll(){
        return service.List();
    }

    @Operation(summary = "addRol")
    @PostMapping("/add")
    public ResponseEntity<String> Add(@RequestBody @Valid Roles rol){
        service.add(rol);
        return ResponseEntity.ok("Rol: "+ rol.getName()+ ", with id: "+ rol.getId() + " added");
    }


}
