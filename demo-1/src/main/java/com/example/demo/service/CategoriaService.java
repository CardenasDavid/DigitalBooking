package com.example.demo.service;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void updateById(int id, Categoria categoria) throws  CategoryNotFoundException{
        repository.findById(id).orElseThrow(()->new CategoryNotFoundException());
        Categoria categoryExist=repository.findById(id).get();
        categoryExist.setTitulo(categoria.getTitulo());
        categoryExist.setDescripcion(categoria.getDescripcion());
        categoryExist.setUrlImagen(categoria.getUrlImagen());
        repository.save(categoryExist);
    }
    public void deleteById(int id) throws CategoryNotFoundException{
        repository.findById(id).orElseThrow(()->new CategoryNotFoundException());
        repository.deleteById(id);
    }



}
