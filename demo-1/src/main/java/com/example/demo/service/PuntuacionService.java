package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Puntuaciones;
import com.example.demo.repository.PuntuacionesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PuntuacionService {
    private final PuntuacionesRepository repository;

    public void add(Puntuaciones puntuaciones){
        repository.save(puntuaciones);
    }
    public List<Puntuaciones> List(){
        return repository.findAll();
    }
    public void deleteById(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Puntuacion no encontrada"));
        repository.deleteById(id);
    }
}
