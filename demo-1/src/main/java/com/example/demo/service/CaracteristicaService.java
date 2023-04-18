package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Caracteristica;
import com.example.demo.repository.CaracteristicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CaracteristicaService {
    private final CaracteristicaRepository repository;

    public void add(Caracteristica caracteristica){
        repository.save(caracteristica);
    }
    public List<Caracteristica> List(){
        return repository.findAll();
    }
    public void deleteById(int id) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Caracteristica no encontrada"));
        repository.deleteById(id);
    }
}
