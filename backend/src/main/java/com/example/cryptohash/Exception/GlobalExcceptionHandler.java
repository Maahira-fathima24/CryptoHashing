package com.example.cryptohash.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException ex) {
        return ex.getMessage();
    }
}
