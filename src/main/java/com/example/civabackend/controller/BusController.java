package com.example.civabackend.controller;

import com.example.civabackend.dto.BusDTO;
import com.example.civabackend.entity.Bus;
import com.example.civabackend.exception.ResourceNotFoundException;
import com.example.civabackend.payload.ApiResponse;
import com.example.civabackend.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    // GET con paginación y filtro de estado
    @GetMapping
    public ResponseEntity<ApiResponse<Page<BusDTO>>> getAllBuses(
            Pageable pageable,
            @RequestParam(required = false) Boolean active) {
        return ResponseEntity.ok(busService.getAllBuses(pageable, active));
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BusDTO>> getBusById(@PathVariable Long id) {
        return ResponseEntity.ok(busService.getBusById(id));
    }
}
