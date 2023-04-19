package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Ciudad;
import com.example.demo.repository.CiudadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CiudadService {

    private final CiudadRepository repository;

    public void add(Ciudad ciudad){
        repository.save(ciudad);
    }

    public List<Ciudad> List(){
        return repository.findAll();
    }

    public void updateById(long id, Ciudad ciudad) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Ciudad no encontrada"));
        Ciudad ciudadExist=repository.findById(id).get();
        ciudadExist.setCiudad(ciudad.getCiudad());
        repository.save(ciudadExist);
    }

    public void deleteById(long id) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Ciudad no encontrada"));
        repository.deleteById(id);
    }

    public Optional<Ciudad> buscarPorId(long id) {
        return repository.findById(id);
    }

    public Optional<Ciudad> buscarPorCiudad(String ciudad) {
        return repository.findByCiudad(ciudad);
    }
}

