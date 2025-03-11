package com.lifetime.orchestrator_service.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerDto(
        Long id,
        String name,
        String lastName,
        String email,
        String phone,
        String location,
        LocalDate birthDate,
        UUID membershipId,
        String clubName
) {
}
