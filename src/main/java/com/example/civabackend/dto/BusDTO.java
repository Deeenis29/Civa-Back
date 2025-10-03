package com.example.civabackend.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusDTO {
    private Long id;
    private String busNumber;
    private String licensePlate;
    private String features;
    private boolean active;
    private LocalDateTime createdAt;
    private BrandDTO brand;
}
