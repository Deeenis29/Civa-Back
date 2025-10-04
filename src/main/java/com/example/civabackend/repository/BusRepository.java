package com.example.civabackend.repository;

import com.example.civabackend.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Page<Bus> findByActive(Boolean active, Pageable pageable);
}
