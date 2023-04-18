package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Imagen;
import com.example.demo.repository.ImagenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImagenService {
    private final ImagenRepository repository;

    public void add(Imagen imagen){
        repository.save(imagen);
    }
    public void update(Imagen imagen){
        repository.save(imagen);
    }
    public List<Imagen> List(){
        return repository.findAll();
    }
    public void deleteById(int id) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Imagen no encontrada"));
        repository.deleteById(id);
    }
}
