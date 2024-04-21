package com.example.lab1.dto;

import com.example.lab1.model.Country;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class HostDto {

    private String name;
    private String surname;
    private Country country;

    public HostDto( String name, String surname, Country country) {

        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public HostDto() {
    }
}
