package com.example.demo.security.detailsService;

import com.example.demo.model.Roles;
import com.example.demo.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String ciudad;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long id, String nombre, String apellido, String email, String ciudad,
                             String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.ciudad = ciudad;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserDetails build(Usuario usuario){
        Set<Roles> roles = usuario.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return new CustomUserDetails(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getCiudad(),
                usuario.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
