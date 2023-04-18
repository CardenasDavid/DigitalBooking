package com.example.demo.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @NotNull
    @NotBlank
    private String nombre;
    @NotNull
    @NotBlank
    private String apellido;
    @NotNull
    @NotBlank
    @Size(max = 70)
    @Email
    private String email;
    @NotBlank
    @NotNull
    @Size(min = 5, max = 40)
    private String password;
    private String ciudad;

    private Set<String> role;

    public void setCiudad(String ciudad) {
        this.ciudad = null;
    }
}
