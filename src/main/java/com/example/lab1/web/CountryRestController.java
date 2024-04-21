package com.example.lab1.web;

import com.example.lab1.dto.CountryDto;
import com.example.lab1.model.Country;
import com.example.lab1.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return countryService.listAll();
    }

    @PostMapping("/addCountry")
    public ResponseEntity<Country> saveCountry(@RequestBody CountryDto countryDto) {
        return countryService.save(countryDto)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/editCountry/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable Long id,
                                              @RequestBody CountryDto countryDto) {
        return countryService.edit(id, countryDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/deleteCountry/{id}")
    public ResponseEntity<Country> deleteCountryById(@PathVariable Long id) {
        countryService.deleteById(id);
        if (countryService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
