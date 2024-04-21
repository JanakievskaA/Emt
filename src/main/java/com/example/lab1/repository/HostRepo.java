package com.example.lab1.repository;

import com.example.lab1.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepo extends JpaRepository<Host,Long> {
}
