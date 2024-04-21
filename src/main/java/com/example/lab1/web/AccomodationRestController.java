package com.example.lab1.web;

import com.example.lab1.dto.AccommodationDto;
import com.example.lab1.model.Accommodation;
import com.example.lab1.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/accommodation")
class AccommodationRestController {

    private final AccommodationService accommodationService;

    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> saveAccommodation(@RequestBody AccommodationDto accommodationDto) {
        return accommodationService.save(accommodationDto)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> editAccommodation (@PathVariable Long id,
                                          @RequestBody AccommodationDto accommodationDto) {
        return accommodationService.edit(id, accommodationDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Accommodation> deleteAccommodationById (@PathVariable Long id) {
        accommodationService.deleteById(id);
        if (accommodationService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<Accommodation> rentAAccommodation(@PathVariable Long id) {
        boolean rentSuccess = accommodationService.markAsRented(id);
        if (rentSuccess) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
