package com.lifetime.orchestrator_service.services;

import com.lifetime.orchestrator_service.dto.EmployeeDto;
import com.lifetime.orchestrator_service.dto.EmployeeRole;
import com.lifetime.orchestrator_service.dto.RegisterEmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "employee-service", path = "/api/employees")
public interface EmployeeServiceClient {

    @PostMapping
    EmployeeDto createEmployee(@RequestBody RegisterEmployeeDto employeeDto);

    @GetMapping
    List<EmployeeDto> getAllEmployees();

    @GetMapping("/{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    EmployeeDto updateEmployee(@PathVariable("id") Long id, @RequestBody RegisterEmployeeDto updateEmployeeDto);

    @PostMapping("/{id}/roles")
    Set<EmployeeRole> addRole(@PathVariable("id") Long id, @RequestBody EmployeeRole role);

    @DeleteMapping("/{id}/roles")
    Set<EmployeeRole> removeRole(@PathVariable("id") Long id, @RequestBody EmployeeRole role);

    @PostMapping("/{employeeId}/assign-expertise")
    EmployeeDto assignExpertiseArea(
            @PathVariable("employeeId") Long employeeId,
            @RequestParam("clubId") Long clubId,
            @RequestParam("expertiseAreaId") Long expertiseAreaId
    );
}