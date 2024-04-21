package com.example.lab1.web;
import com.example.lab1.dto.HostDto;
import com.example.lab1.model.Host;
import com.example.lab1.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/hosts")

public class HostRestController {
    private final HostService hostService;

    public HostRestController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> findAll(){
        return hostService.findAll();
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<Host> saveHost(@RequestBody HostDto hostDto) {
        return hostService.save(hostDto)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/editAuthor/{id}")
    public ResponseEntity<Host> editHost (@PathVariable Long id,
                                              @RequestBody HostDto hostDto) {
        return hostService.edit(id, hostDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<Host> deleteHostById (@PathVariable Long id) {
        hostService.deleteById(id);
        if (hostService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
