package com.lifetime.orchestrator_service.dto;

import java.time.LocalDate;
import java.util.UUID;

public record MembershipDto(
        UUID uuid,
        MembershipType type,
        LocalDate startDate,
        LocalDate endDate
) {
}
