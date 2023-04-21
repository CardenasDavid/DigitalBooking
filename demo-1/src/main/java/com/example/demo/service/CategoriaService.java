package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;

    public void add(Categoria categoria){
        repository.save(categoria);
    }
    public List<Categoria> List(){
        return repository.findAll();
    }

    public void updateById(int id, Categoria categoria) throws  NotFoundException{
        Categoria categoryExist= repository.findById(id)
                .orElseThrow(()->new NotFoundException("Categoria no encontrada"));
        BeanUtils.copyProperties(categoria,categoryExist,"id");
        repository.save(categoryExist);
    }
    public void deleteById(int id) throws NotFoundException{
        repository.findById(id).orElseThrow(()->new NotFoundException("Categoria no encontrada"));
        repository.deleteById(id);
    }



}
