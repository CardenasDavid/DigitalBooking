package com.example.demo.exceptions;

import com.example.demo.controller.CategoriaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    private static final Logger logger= LoggerFactory.getLogger(CategoriaController.class);

    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<?>error(CategoryNotFoundException exception){
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> error(MethodArgumentNotValidException exception){
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> error(NotFoundException exception){
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> error(BadRequestException exception){
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
