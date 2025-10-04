package com.example.civabackend.service;

import com.example.civabackend.dto.BusDTO;
import com.example.civabackend.dto.BrandDTO;
import com.example.civabackend.entity.Bus;
import com.example.civabackend.payload.ApiResponse;
import com.example.civabackend.repository.BusRepository;
import com.example.civabackend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    // ✅ Paginación con filtro de estado
    public ApiResponse<Page<BusDTO>> getAllBuses(Pageable pageable, Boolean active) {
        Page<BusDTO> page;
        if (active != null) {
            page = busRepository.findByActive(active, pageable).map(this::mapToDTO);
        } else {
            page = busRepository.findAll(pageable).map(this::mapToDTO);
        }

        if (page.isEmpty()) {
            return ApiResponse.<Page<BusDTO>>builder()
                    .success(false)
                    .message("No hay resultados para la página solicitada")
                    .data(page)
                    .build();
        }

        return ApiResponse.<Page<BusDTO>>builder()
                .success(true)
                .message("Buses obtenidos correctamente")
                .data(page)
                .build();
    }

    // ✅ Buscar por ID con manejo de error
    public ApiResponse<BusDTO> getBusById(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el bus con id " + id));

        return ApiResponse.<BusDTO>builder()
                .success(true)
                .message("Bus encontrado")
                .data(mapToDTO(bus))
                .build();
    }

    // Mapper de entidad a DTO
    private BusDTO mapToDTO(Bus bus) {
        return BusDTO.builder()
                .id(bus.getId())
                .busNumber(bus.getBusNumber())
                .licensePlate(bus.getLicensePlate())
                .features(bus.getFeatures())
                .active(bus.isActive())
                .createdAt(bus.getCreatedAt())
                .imageURL(bus.getImageURL())
                .brand(BrandDTO.builder()
                        .id(bus.getBrand().getId())
                        .name(bus.getBrand().getName())
                        .build())
                .build();
    }
}
