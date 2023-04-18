package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public List<Usuario> List(){
        return repository.findAll();
    }
    public void add(Usuario usuario){
        repository.save(usuario);
    }
    public void update(Usuario usuario){ repository.save(usuario);
    }
    public void deleteById(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado") );
        repository.deleteById(id);}


    public Usuario getById(Long id){
        return repository.getReferenceById(id);
    }
}
