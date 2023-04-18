package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String email;
    private List<String> roles;
    private String jwt;

}
