package com.example.lab1.dto;

import com.example.lab1.model.Host;
import com.example.lab1.model.enumerations.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AccommodationDto {
    private String name;
    private int numRooms;
    private Host host;
    private Category category;


    public AccommodationDto(String name, int numRooms, Host host, Category category) {
        this.name = name;
        this.numRooms = numRooms;
        this.host = host;
        this.category = category;
    }

    public AccommodationDto() {
    }
}
