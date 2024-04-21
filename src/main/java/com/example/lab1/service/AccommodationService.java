package com.example.lab1.service;

import com.example.lab1.dto.AccommodationDto;
import com.example.lab1.model.Accommodation;
import com.example.lab1.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(AccommodationDto accommodationDto);

    Accommodation create(String name,Category category,Long hostId,Integer numRooms);

    Optional<Accommodation> edit(Long id, AccommodationDto accommodationDto);

    void deleteById(Long id);

    boolean markAsRented(Long id);

}
