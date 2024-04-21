package com.example.lab1.service.impl;

import com.example.lab1.dto.AccommodationDto;
import com.example.lab1.dto.HostDto;
import com.example.lab1.model.Accommodation;
import com.example.lab1.model.Host;
import com.example.lab1.model.enumerations.Category;
import com.example.lab1.model.exceptions.AccomodationNotFound;
import com.example.lab1.model.exceptions.HostNotFound;
import com.example.lab1.repository.AccomodationRepo;
import com.example.lab1.repository.HostRepo;
import com.example.lab1.service.AccommodationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccomodationRepo accomodationRepo;
    private  final HostRepo hostRepo;




    public AccommodationServiceImpl(AccomodationRepo accomodationRepo,HostRepo hostRepo) {
        this.accomodationRepo = accomodationRepo;
        this.hostRepo = hostRepo;

    }

    @Override
    public List<Accommodation> findAll() {
        return accomodationRepo.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accomodationRepo.findById(id);
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodationDto) {
        Host host = hostRepo.findById(accommodationDto.getHost().getId()).orElseThrow(() -> new HostNotFound(accommodationDto.getHost().getId()));

        Accommodation accommodation = new Accommodation(accommodationDto.getName(), accommodationDto.getCategory(), host, accommodationDto.getNumRooms());
        accomodationRepo.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Accommodation create(String name, Category category, Long hostId, Integer numRooms) {
        Host host = hostRepo.findById(hostId).orElseThrow(() -> new HostNotFound(hostId));
        Accommodation accommodation = new Accommodation(name, category, host, numRooms);
        accomodationRepo.save(accommodation);
        return accommodation;
    }

    @Override
    public Optional<Accommodation> edit(Long id, AccommodationDto accommodationDto) {
        Host host = hostRepo.findById(accommodationDto.getHost().getId()).orElseThrow(() -> new HostNotFound(accommodationDto.getHost().getId()));
        Accommodation accommodation = accomodationRepo.findById(id).orElseThrow(() -> new AccomodationNotFound(id));

        accommodation.setName(accommodation.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodation.getNumRooms());

        accomodationRepo.save(accommodation);

        return Optional.of(accommodation);
    }


    @Override
    public void deleteById(Long id) {
        Accommodation accommodation= accomodationRepo.findById(id).orElseThrow(() -> new AccomodationNotFound(id));
        accomodationRepo.deleteById(id);

    }

    @Override
    public boolean markAsRented(Long id) {
        Accommodation accommodation = accomodationRepo.findById(id).orElseThrow(() -> new AccomodationNotFound(id));
        int copies = accommodation.getNumRooms();

        if (copies > 0) {
            accommodation.setNumRooms(accommodation.getNumRooms() - 1);
            accomodationRepo.save(accommodation);
            return  true;
        }

        return false;
    }
}
