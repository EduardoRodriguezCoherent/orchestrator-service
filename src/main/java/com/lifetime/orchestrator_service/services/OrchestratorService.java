package com.lifetime.orchestrator_service.services;

import com.lifetime.orchestrator_service.dto.CustomerDto;
import com.lifetime.orchestrator_service.dto.RegisterCustomerNoClub;

public interface OrchestratorService {
    CustomerDto registerCustomerWithClub(RegisterCustomerNoClub registerCustomerNoClub);
}
