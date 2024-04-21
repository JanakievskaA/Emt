package com.example.lab1.config;

import com.example.lab1.model.enumerations.Category;
import com.example.lab1.service.AccommodationService;
import com.example.lab1.service.CountryService;
import com.example.lab1.service.HostService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitalizer {
    private final AccommodationService accommodationService;

    private final HostService hostService;
    private final CountryService countryService;


    public DataInitalizer(AccommodationService accommodationService, HostService hostService, CountryService create, CountryService countryService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData() {

        Category[] categories = Category.values();
        for (int i = 1; i < 6; i++){
            countryService.create("Country " + i, "Continent " + i);

            hostService.create("HostName " + i, "HostSurname" + i, (long) ((i - 1) % 5 + 1));

            accommodationService.create("Accommodation " + i, categories[(i - 1) % categories.length], (long) ((i - 1) % 5 + 1), 5 + i);

        }
    }
}
