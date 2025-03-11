package com.lifetime.orchestrator_service.dto;

import java.time.LocalDate;
import java.util.Set;

public record EmployeeDto(
        Long id,
        String name,
        String lastName,
        LocalDate birthDate,
        Set<EmployeeRole> roles,
        Set<Long> expertiseAreas // Facility IDs
) {
}
