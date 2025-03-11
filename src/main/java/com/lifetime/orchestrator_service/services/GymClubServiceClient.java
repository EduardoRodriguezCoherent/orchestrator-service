package com.lifetime.orchestrator_service.services;

import com.lifetime.orchestrator_service.dto.GymClubDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "gym-club-service", path = "/api/gym-clubs")
public interface GymClubServiceClient {

    @GetMapping
    List<GymClubDto> getAllGymClubs();
}
