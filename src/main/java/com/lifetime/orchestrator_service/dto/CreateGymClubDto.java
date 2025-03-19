package com.lifetime.orchestrator_service.dto;

public record CreateGymClubDto(
        String name,
        String location,
        long discount
) {}
