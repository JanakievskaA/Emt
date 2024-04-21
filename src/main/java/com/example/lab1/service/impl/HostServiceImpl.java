package com.example.lab1.service.impl;

import com.example.lab1.dto.HostDto;
import com.example.lab1.model.Country;
import com.example.lab1.model.Host;
import com.example.lab1.model.exceptions.ConutryNotFound;
import com.example.lab1.model.exceptions.HostNotFound;
import com.example.lab1.repository.CountryRepo;
import com.example.lab1.repository.HostRepo;
import com.example.lab1.service.HostService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepo hostRepo;
    private final CountryRepo countryRepo;

    public HostServiceImpl(HostRepo hostRepo, CountryRepo countryRepo) {
        this.hostRepo = hostRepo;
        this.countryRepo = countryRepo;
    }

    @Override
    public List<Host> findAll() {
        return hostRepo.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepo.findById(id);
    }

    @Override
    public Optional<Host> save(HostDto hostDto) {
        Country country = countryRepo.findById(hostDto.getCountry().getId()).orElseThrow(() -> new ConutryNotFound(hostDto.getCountry().getId()));
        Host host = new Host(hostDto.getName(), hostDto.getSurname(), country);
        hostRepo.save(host);
        return Optional.of(host);
    }

    @Override
    public Host create(String name, String surname, Long countryId) {
        Country country = countryRepo.findById(countryId).orElseThrow(() -> new ConutryNotFound(countryId));
        Host host = new Host(name, surname, country);
        hostRepo.save(host);
        return host;
    }

    @Override
    public Optional<Host> edit(Long id, HostDto hostDto) {
        Country country = countryRepo.findById(hostDto.getCountry().getId()).orElseThrow(() -> new ConutryNotFound(hostDto.getCountry().getId()));
        Host host = hostRepo.findById(id).orElseThrow(() -> new HostNotFound(id));

        host.setName(hostDto.getName());
        host.setSurname(hostDto.getSurname());
        host.setCountry(country);

        hostRepo.save(host);
        return Optional.of(host);
    }

    @Override
    public void deleteById(Long id) {
        hostRepo.deleteById(id);
    }
}
