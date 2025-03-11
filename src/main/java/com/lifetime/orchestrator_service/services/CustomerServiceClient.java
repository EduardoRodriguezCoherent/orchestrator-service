package com.lifetime.orchestrator_service.services;

import com.lifetime.orchestrator_service.dto.CustomerDto;
import com.lifetime.orchestrator_service.dto.RegisterCustomerWithClub;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "customer-service", path = "/api/customers")
public interface CustomerServiceClient {

    @GetMapping("/{id}")
    CustomerDto getCustomerById(@PathVariable("id") Long id);

    @PostMapping("/register")
    CustomerDto registerCustomer(@RequestBody RegisterCustomerWithClub registerCustomerWithClub);

    @GetMapping
    List<CustomerDto> getAllCustomers();

    @GetMapping("/validate-email")
    Boolean validateEmail(@RequestParam("email") String email);
}

