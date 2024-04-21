package com.example.lab1.service.impl;

import com.example.lab1.dto.CountryDto;
import com.example.lab1.model.Country;
import com.example.lab1.model.exceptions.ConutryNotFound;
import com.example.lab1.repository.CountryRepo;
import com.example.lab1.service.CountryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{
    private final CountryRepo countryRepo;

    public CountryServiceImpl(CountryRepo countryRepository) {
        this.countryRepo = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepo.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepo.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        countryRepo.save(country);
        return Optional.of(country);
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name, continent);
        countryRepo.save(country);
        return country;
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = countryRepo.findById(id).orElseThrow(() -> new ConutryNotFound(id));

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        countryRepo.save(country);
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        countryRepo.deleteById(id);
    }
}
