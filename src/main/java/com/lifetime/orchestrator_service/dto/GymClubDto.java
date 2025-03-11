package com.lifetime.orchestrator_service.dto;

import java.util.List;

public record GymClubDto(
        Long id,
        String name,
        String location,
        long discount,
        List<FacilityDto> facilities
) {
}
