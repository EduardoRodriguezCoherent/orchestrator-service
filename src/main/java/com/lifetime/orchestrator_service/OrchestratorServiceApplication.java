package com.lifetime.orchestrator_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Orchestrator Service API",
                version = "1.0",
                description = "This API handles orchestration logic for distributed services"
        )
)
public class OrchestratorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchestratorServiceApplication.class, args);
    }

}
