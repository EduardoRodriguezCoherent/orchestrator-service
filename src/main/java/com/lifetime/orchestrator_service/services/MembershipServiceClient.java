package com.lifetime.orchestrator_service.services;

import com.lifetime.orchestrator_service.dto.MembershipDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "membership-service", path = "/api/memberships")
public interface MembershipServiceClient {

    @PutMapping("/{uuid}/upgrade")
    MembershipDto upgradeMembership(@PathVariable("uuid") UUID uuid);

    @PutMapping("/{uuid}/downgrade")
    MembershipDto downgradeMembership(@PathVariable("uuid") UUID uuid);

    @GetMapping
    List<MembershipDto> getAllMemberships();
}
