package com.example.demo.service;


import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;

    public Producto add(Producto producto){
        return repository.save(producto);
    }
    public void update(Producto producto){
        repository.save(producto);
    }
    public void updateById(int id, Producto producto) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Producto no encontrado"));
        Producto productExist=repository.findById(id).get();
        productExist.setTitulo(producto.getTitulo());
        productExist.setUbicacion(producto.getUbicacion());
        productExist.setDescripcion(producto.getDescripcion());
        productExist.setDireccion(producto.getDireccion());
        productExist.setCategoria(producto.getCategoria());
        productExist.setCiudad(producto.getCiudad());
        productExist.setImagenes(producto.getImagenes());
        productExist.setCaracteristicas(producto.getCaracteristicas());
        repository.save(productExist);
    }

    public List<Producto>List(){
        return repository.findAll();
    }

     public Producto getById(int id){
        return repository.getReferenceById(id);
     }
    public void deleteById(int id) throws NotFoundException{
        repository.findById(id).orElseThrow(()->new NotFoundException("Producto no encontrado") );
        repository.deleteById(id);
    }
     public List<Producto>findProductByCity(String city){
         return repository.findByCiudadCiudad(city);
     }
    public List<Producto>findProductByCategory(String title) {
        return repository.findByCategoriaTitulo(title);
    }
    public List<Producto>listByDates(LocalDate startDate,LocalDate endDate){
        return repository.listByDates(startDate,endDate);
    }
    public List<Producto>listByDatesAndCity(String city, LocalDate startDate,LocalDate endDate){
        var listByDatesCity= repository.listByDates(startDate,endDate);
        listByDatesCity.removeIf(p -> !p.getCiudad().getCiudad().equals(city));
        return listByDatesCity;
    }

}
