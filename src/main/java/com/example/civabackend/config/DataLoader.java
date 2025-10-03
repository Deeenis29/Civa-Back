package com.example.civabackend.config;

import com.example.civabackend.entity.Brand;
import com.example.civabackend.entity.Bus;
import com.example.civabackend.repository.BrandRepository;
import com.example.civabackend.repository.BusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(BrandRepository brandRepository, BusRepository busRepository) {
        return args -> {
            if (brandRepository.count() == 0 && busRepository.count() == 0) {
                // Insertar marcas
                Brand volvo = brandRepository.save(Brand.builder().name("Volvo").build());
                Brand scania = brandRepository.save(Brand.builder().name("Scania").build());
                Brand mercedes = brandRepository.save(Brand.builder().name("Mercedes Benz").build());

                // Volvo: buses 101 - 109
                for (int i = 101; i <= 109; i++) {
                    busRepository.save(Bus.builder()
                            .active(true)
                            .busNumber(String.valueOf(i))
                            .createdAt(LocalDateTime.now())
                            .features("Marcopolo G8 - 2 pisos, 15m de longitud, 60 asientos, Tecnología ERV, Antiniebla Full LED, Wi-Fi, A/C, USB")
                            .lincensePlate("VOL-" + i)
                            .brand(volvo)
                            .build());
                }

                // Scania: buses 201 - 209
                for (int i = 201; i <= 209; i++) {
                    busRepository.save(Bus.builder()
                            .active(true)
                            .busNumber(String.valueOf(i))
                            .createdAt(LocalDateTime.now())
                            .features("Marcopolo G8 - 2 pisos, 15m de longitud, 60 asientos, Tecnología ERV, Antiniebla Full LED, Wi-Fi, A/C, USB")
                            .lincensePlate("SCA-" + i)
                            .brand(scania)
                            .build());
                }

                // Mercedes: buses 301 - 309
                for (int i = 301; i <= 309; i++) {
                    busRepository.save(Bus.builder()
                            .active(true)
                            .busNumber(String.valueOf(i))
                            .createdAt(LocalDateTime.now())
                            .features("Marcopolo G8 - 2 pisos, 15m de longitud, 60 asientos, Tecnología ERV, Antiniebla Full LED, Wi-Fi, A/C, USB")
                            .lincensePlate("MER-" + i)
                            .brand(mercedes)
                            .build());
                }

                System.out.println("✅ Datos iniciales insertados correctamente (Brands + 27 Buses).");
            }
        };
    }
}
