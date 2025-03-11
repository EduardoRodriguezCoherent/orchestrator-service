package com.lifetime.orchestrator_service.services.impl;

import com.lifetime.orchestrator_service.dto.CustomerDto;
import com.lifetime.orchestrator_service.dto.GymClubDto;
import com.lifetime.orchestrator_service.dto.RegisterCustomerNoClub;
import com.lifetime.orchestrator_service.dto.RegisterCustomerWithClub;
import com.lifetime.orchestrator_service.services.CustomerServiceClient;
import com.lifetime.orchestrator_service.services.GymClubServiceClient;
import com.lifetime.orchestrator_service.services.OrchestratorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrchestratorServiceImpl implements OrchestratorService {

    private static final String DEFAULT_CLUB = "Minnetonka";
    private final Random random = new Random();
    private final CustomerServiceClient customerServiceClient;
    private final GymClubServiceClient gymClubServiceClient;

    public OrchestratorServiceImpl(CustomerServiceClient customerServiceClient,
                                   GymClubServiceClient gymClubServiceClient) {
        this.customerServiceClient = customerServiceClient;
        this.gymClubServiceClient = gymClubServiceClient;
    }

    @Override
    public CustomerDto registerCustomerWithClub(RegisterCustomerNoClub registerCustomerNoClub) {

        String clubName = DEFAULT_CLUB;
        Map<String, String> clubNameLocationMap = gymClubServiceClient.getAllGymClubs().stream()
                .collect(Collectors.toMap(GymClubDto::name, GymClubDto::location));

        List<String> possibleLocation = clubNameLocationMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(registerCustomerNoClub.location()))
                .map(Map.Entry::getKey)
                .toList();

        if (!possibleLocation.isEmpty()) {
            clubName = possibleLocation.get(random.nextInt(possibleLocation.size()));
        }

        RegisterCustomerWithClub fullCustomer = new RegisterCustomerWithClub(
                registerCustomerNoClub.name(),
                registerCustomerNoClub.lastName(),
                registerCustomerNoClub.email(),
                registerCustomerNoClub.password(),
                registerCustomerNoClub.phone(),
                registerCustomerNoClub.location(),
                registerCustomerNoClub.birthDate(),
                clubName
        );

        return customerServiceClient.registerCustomer(fullCustomer);
    }
}
