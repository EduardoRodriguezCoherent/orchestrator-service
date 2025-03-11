package com.lifetime.orchestrator_service.controllers;

import com.lifetime.orchestrator_service.dto.CustomerDto;
import com.lifetime.orchestrator_service.dto.EmployeeDto;
import com.lifetime.orchestrator_service.dto.GymClubDto;
import com.lifetime.orchestrator_service.dto.MembershipDto;
import com.lifetime.orchestrator_service.dto.RegisterCustomerNoClub;
import com.lifetime.orchestrator_service.dto.RegisterEmployeeDto;
import com.lifetime.orchestrator_service.services.CustomerServiceClient;
import com.lifetime.orchestrator_service.services.EmployeeServiceClient;
import com.lifetime.orchestrator_service.services.GymClubServiceClient;
import com.lifetime.orchestrator_service.services.MembershipServiceClient;
import com.lifetime.orchestrator_service.services.OrchestratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orchestrator")
public class RootController {

    private final OrchestratorService orchestratorService;
    private final CustomerServiceClient customerServiceClient;
    private final EmployeeServiceClient employeeServiceClient;
    private final GymClubServiceClient gymClubServiceClient;
    private final MembershipServiceClient membershipServiceClient;

    public RootController(OrchestratorService orchestratorService,
                          CustomerServiceClient customerServiceClient,
                          EmployeeServiceClient employeeServiceClient,
                          GymClubServiceClient gymClubServiceClient,
                          MembershipServiceClient membershipServiceClient) {
        this.orchestratorService = orchestratorService;
        this.customerServiceClient = customerServiceClient;
        this.employeeServiceClient = employeeServiceClient;
        this.gymClubServiceClient = gymClubServiceClient;
        this.membershipServiceClient = membershipServiceClient;
    }

    @PostMapping("/register-customer")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody RegisterCustomerNoClub registerCustomerNoClub) {
        CustomerDto newCustomer = orchestratorService.registerCustomerWithClub(registerCustomerNoClub);
        return ResponseEntity.ok(newCustomer);
    }

    @PostMapping("/register-employee")
    public ResponseEntity<EmployeeDto> registerCustomer(@RequestBody RegisterEmployeeDto registerEmployeeDto) {
        EmployeeDto employeeDto = employeeServiceClient.createEmployee(registerEmployeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/clubs")
    public ResponseEntity<List<GymClubDto>> getAllClubs() {
        return ResponseEntity.ok(gymClubServiceClient.getAllGymClubs());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerServiceClient.getAllCustomers());
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeServiceClient.getAllEmployees());
    }

    @GetMapping("/memberships")
    public ResponseEntity<List<MembershipDto>> getAllMemberships() {
        return ResponseEntity.ok(membershipServiceClient.getAllMemberships());
    }

    @PutMapping("/memberships/{uuid}/upgrade-membership")
    public ResponseEntity<MembershipDto> upgradeMembership(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(membershipServiceClient.upgradeMembership(uuid));
    }

    @PutMapping("/memberships/{uuid}/downgrade-membership")
    ResponseEntity<MembershipDto> downgradeMembership(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(membershipServiceClient.downgradeMembership(uuid));
    }
}