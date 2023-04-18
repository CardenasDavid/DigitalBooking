package com.example.demo.controller;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.ERole;
import com.example.demo.model.Roles;
import com.example.demo.model.Usuario;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.request.UserCity;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.detailsService.CustomUserDetails;
import com.example.demo.security.detailsService.CustomUserDetailsService;
import com.example.demo.security.jwt.JwtUtil;
import com.example.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/auth/user")
@Tag(name = "1. User Controller")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    RolesRepository roleRepository;
    @Autowired
    UsuarioService service;

    private final CustomUserDetailsService  customUserDetailsService;

@GetMapping("/list")
public List<Usuario> getAll(){
    return service.List();
}
    @Operation(summary = "User SignIn")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),userDetails.getNombre(),userDetails.getApellido(),userDetails.getCiudad(),
                userDetails.getEmail(), roles, jwt));
    }

    @Operation(summary = "User registration: SignUp")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new BadRequestException("Error: Email is already taken!"));
        }
        Usuario usuario = new Usuario(signUpRequest.getNombre(), signUpRequest.getApellido(),
                signUpRequest.getCiudad(),signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Roles> roles = new HashSet<>();

        if (strRoles == null) {
            Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Roles adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "MOD":
                        Roles modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("User: "+ usuario.getNombre()+ ", " +
                "with email: "+ usuario.getEmail() + " registered successfully.");
    }
    @Operation(summary = "Update User's city")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCity(@PathVariable long id , @Valid @RequestBody UserCity userCity) throws com.example.demo.exceptions.NotFoundException {
        customUserDetailsService.updateCity(id, userCity);
        return ResponseEntity.ok("City: "+ userCity.getCiudad() +", added to user.");
    }



}
