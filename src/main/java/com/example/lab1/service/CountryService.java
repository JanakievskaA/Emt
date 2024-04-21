package com.example.lab1.service;

import com.example.lab1.dto.CountryDto;
import com.example.lab1.model.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    Country create(String name,  String continent);
    Optional<Country> edit(Long id, CountryDto countryDto);

    void deleteById(Long id);
}
