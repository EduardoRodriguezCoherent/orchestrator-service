package com.lifetime.orchestrator_service.dto;

import java.time.LocalDate;

public record RegisterCustomerNoClub(
        String name,
        String lastName,
        String email,
        String password,
        String phone,
        String location,
        LocalDate birthDate
) {
}
