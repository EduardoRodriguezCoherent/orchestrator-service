package com.lifetime.orchestrator_service.services;

import com.lifetime.orchestrator_service.dto.CreateGymClubDto;
import com.lifetime.orchestrator_service.dto.GymClubDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "gym-club-service", path = "/api/gym-clubs")
public interface GymClubServiceClient {

    @GetMapping
    List<GymClubDto> getAllGymClubs();

    @GetMapping("/{id}")
    GymClubDto getGymClubById(@PathVariable("id") Long id);

    @PostMapping
    GymClubDto createGymClub(@RequestBody CreateGymClubDto dto);

    @PutMapping
    GymClubDto updateGymClub(@RequestBody GymClubDto dto);
}
