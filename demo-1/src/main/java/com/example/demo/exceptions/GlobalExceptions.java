package com.example.demo.exceptions;

import com.example.demo.controller.CategoriaController;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptions {
    private static final Logger logger= LoggerFactory.getLogger(CategoriaController.class);

    @ExceptionHandler({MethodArgumentNotValidException.class, BadRequestException.class})
    public ResponseEntity<ApiError> error(MethodArgumentNotValidException exception, HttpServletRequest request){
        ApiError apiError= new ApiError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ApiError> error(NotFoundException exception, HttpServletRequest request){
        ApiError apiError= new ApiError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
