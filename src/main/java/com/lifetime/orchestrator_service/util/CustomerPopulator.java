package com.lifetime.orchestrator_service.util;

import com.lifetime.orchestrator_service.dto.RegisterCustomerNoClub;
import com.lifetime.orchestrator_service.services.CustomerServiceClient;
import com.lifetime.orchestrator_service.services.OrchestratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CustomerPopulator implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CustomerPopulator.class);

    private static final int CUSTOMERS_TO_ADD = 10_000;
    private final OrchestratorService orchestratorService;
    private final CustomerServiceClient customerServiceClient;

    public CustomerPopulator(OrchestratorService orchestratorService,
                             CustomerServiceClient customerServiceClient) {
        this.orchestratorService = orchestratorService;
        this.customerServiceClient = customerServiceClient;
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i <= CUSTOMERS_TO_ADD; i++) {
            RegisterCustomerNoClub newCustomer = new RegisterCustomerNoClub(
                    "name" + i,
                    "lastName" + i,
                    "email" + i + "@domain.com",
                    "password" + i,
                    String.format("%010d", i),
                    "ON",
                    LocalDate.of(1999, 10, 13)
            );

            boolean existsEmail = customerServiceClient.validateEmail(newCustomer.email());
            if (!existsEmail) {
                orchestratorService.registerCustomerWithClub(newCustomer);
                log.info("Added new customer with email {}", newCustomer.email());
            } else {
                log.warn("Customer with email {} already exists.", newCustomer.email());
            }
        }
    }
}
