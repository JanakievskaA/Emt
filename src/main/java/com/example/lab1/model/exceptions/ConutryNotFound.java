package com.example.lab1.model.exceptions;

public class ConutryNotFound extends RuntimeException{
    public  ConutryNotFound(Long id) {
        super(String.format("Country with id: %d is not found", id));
    }
}
