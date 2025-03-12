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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "orchestrator-service", description = "Endpoints for using the Gym Management System ")
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

    @Operation(
            summary = "Register a new customer",
            description = "Registers a new customer without assigning a club. Returns the created customer details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer successfully registered",
                            content = @Content(schema = @Schema(implementation = CustomerDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @PostMapping("/register-customer")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody RegisterCustomerNoClub registerCustomerNoClub) {
        CustomerDto newCustomer = orchestratorService.registerCustomerWithClub(registerCustomerNoClub);
        return ResponseEntity.ok(newCustomer);
    }

    @Operation(
            summary = "Register a new employee",
            description = "Creates a new employee record and returns the employee details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee successfully registered",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid employee details"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @PostMapping("/register-employee")
    public ResponseEntity<EmployeeDto> registerEmployee(@RequestBody RegisterEmployeeDto registerEmployeeDto) {
        EmployeeDto employeeDto = employeeServiceClient.createEmployee(registerEmployeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    @Operation(summary = "Get all gym clubs", description = "Retrieves a list of all registered gym clubs.")
    @ApiResponse(responseCode = "200", description = "List of gym clubs returned successfully")
    @GetMapping("/clubs")
    public ResponseEntity<List<GymClubDto>> getAllClubs() {
        return ResponseEntity.ok(gymClubServiceClient.getAllGymClubs());
    }

    @Operation(summary = "Get all customers", description = "Fetches a list of all registered customers.")
    @ApiResponse(responseCode = "200", description = "List of customers returned successfully")
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerServiceClient.getAllCustomers());
    }

    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees in the system.")
    @ApiResponse(responseCode = "200", description = "List of employees returned successfully")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeServiceClient.getAllEmployees());
    }

    @Operation(summary = "Get all memberships", description = "Returns all membership details.")
    @ApiResponse(responseCode = "200", description = "List of memberships returned successfully")
    @GetMapping("/memberships")
    public ResponseEntity<List<MembershipDto>> getAllMemberships() {
        return ResponseEntity.ok(membershipServiceClient.getAllMemberships());
    }

    @Operation(
            summary = "Upgrade membership",
            description = "Upgrades a customer's membership to GOLD.",
            parameters = {
                    @Parameter(name = "uuid", description = "Unique ID of the membership to upgrade", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Membership upgraded successfully",
                            content = @Content(schema = @Schema(implementation = MembershipDto.class))),
                    @ApiResponse(responseCode = "404", description = "Membership not found"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @PutMapping("/memberships/{uuid}/upgrade-membership")
    public ResponseEntity<MembershipDto> upgradeMembership(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(membershipServiceClient.upgradeMembership(uuid));
    }

    @Operation(
            summary = "Downgrade membership",
            description = "Downgrades a customer's membership to BASIC.",
            parameters = {
                    @Parameter(name = "uuid", description = "Unique ID of the membership to downgrade", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Membership downgraded successfully",
                            content = @Content(schema = @Schema(implementation = MembershipDto.class))),
                    @ApiResponse(responseCode = "404", description = "Membership not found"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @PutMapping("/memberships/{uuid}/downgrade-membership")
    public ResponseEntity<MembershipDto> downgradeMembership(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(membershipServiceClient.downgradeMembership(uuid));
    }
}