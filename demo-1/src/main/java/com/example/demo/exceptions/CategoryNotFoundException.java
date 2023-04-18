package com.example.demo.exceptions;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(){
        super("Categoria no encontrada");
    }
}
