package com.example.lab1.model;

import com.example.lab1.model.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer numRooms;
    @ManyToOne
    private Host host;
    @Enumerated (EnumType.STRING)
    private Category category;

    public Accommodation(String name, Category category, Host host, Integer numRooms) {

        this.name = name;
        this.numRooms = numRooms;
        this.host = host;
        this.category = category;
    }

    public Accommodation() {
    }
}
