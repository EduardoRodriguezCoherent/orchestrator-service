package com.lifetime.orchestrator_service.services;

import com.lifetime.orchestrator_service.dto.EmployeeDto;
import com.lifetime.orchestrator_service.dto.RegisterEmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "employee-service", path = "/api/employees")
public interface EmployeeServiceClient {

    @PostMapping
    EmployeeDto createEmployee(@RequestBody RegisterEmployeeDto employeeDto);

    @GetMapping
    List<EmployeeDto> getAllEmployees();
}