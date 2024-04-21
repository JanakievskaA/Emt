package com.example.lab1.model.exceptions;

public class AccomodationNotFound extends RuntimeException {
    public AccomodationNotFound(Long id) {
        super(String.format("Acc with id: %d is not found", id));
    }
}
