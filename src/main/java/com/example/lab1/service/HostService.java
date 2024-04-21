package com.example.lab1.service;

import com.example.lab1.dto.HostDto;
import com.example.lab1.model.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(HostDto hostDto);
    Host create(String name, String surname, Long countryId);
    Optional<Host> edit(Long id, HostDto hostDto);

    void deleteById(Long id);

}
