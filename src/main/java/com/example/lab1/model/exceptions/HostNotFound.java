package com.example.lab1.model.exceptions;

public class HostNotFound extends RuntimeException{
    public HostNotFound(Long id) {
        super(String.format("Host with id: %d is not found", id));
    }
}
