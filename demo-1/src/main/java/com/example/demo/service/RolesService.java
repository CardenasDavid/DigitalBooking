package com.example.demo.service;


import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Producto;
import com.example.demo.model.Roles;
import com.example.demo.model.Usuario;
import com.example.demo.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RolesService {
    private final RolesRepository repository;

    public List<Roles> List(){
        return repository.findAll();
    }
    public void add(Roles roles){
        repository.save(roles);
    }
    public void update(Roles roles){ repository.save(roles);
    }
    public void deleteById(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Rol no encontrado") );
        repository.deleteById(id);}


    public Roles getById(Long id){
        return repository.getReferenceById(id);
    }

}
