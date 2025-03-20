package com.lifetime.orchestrator_service.controllers;

import com.lifetime.orchestrator_service.dto.CreateGymClubDto;
import com.lifetime.orchestrator_service.dto.CustomerDto;
import com.lifetime.orchestrator_service.dto.EmployeeDto;
import com.lifetime.orchestrator_service.dto.EmployeeRole;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
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

    @Operation(summary = "Get all gym clubs", description = "Retrieves a list of all registered gym clubs.")
    @ApiResponse(responseCode = "200", description = "List of gym clubs returned successfully")
    @GetMapping("/clubs")
    public ResponseEntity<List<GymClubDto>> getAllClubs() {
        return ResponseEntity.ok(gymClubServiceClient.getAllGymClubs());
    }

    @Operation(summary = "Get an specific club", description = "Returns the club details.")
    @ApiResponse(responseCode = "200", description = "Gym club returned successfully")
    @GetMapping("/clubs/{id}")
    public ResponseEntity<GymClubDto> getClubById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(gymClubServiceClient.getGymClubById(id));
    }

    @Operation(
            summary = "Create a new Gym Club",
            description = "Creates a new club. Returns the created club details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gym Club successfully created",
                            content = @Content(schema = @Schema(implementation = GymClubDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @PostMapping("/clubs/create-club")
    public ResponseEntity<GymClubDto> createClub(@RequestBody CreateGymClubDto createGymClubDto) {
        return ResponseEntity.ok(gymClubServiceClient.createGymClub(createGymClubDto));
    }

    @Operation(
            summary = "Updates a Gym Club",
            description = "Updates a club. Returns the updated club details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gym Club successfully updated",
                            content = @Content(schema = @Schema(implementation = GymClubDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @PutMapping("/clubs/update-club")
    public ResponseEntity<GymClubDto> updateClub(@RequestBody GymClubDto gymClubDto) {
        return ResponseEntity.ok(gymClubServiceClient.updateGymClub(gymClubDto));
    }









    @Operation(summary = "Get all customers", description = "Fetches a list of all registered customers.")
    @ApiResponse(responseCode = "200", description = "List of customers returned successfully")
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerServiceClient.getAllCustomers());
    }

    @Operation(summary = "Get a specific customer", description = "Returns the customer details.")
    @ApiResponse(responseCode = "200", description = "Customer returned successfully")
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerServiceClient.getCustomerById(id));
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
    @PostMapping("/customers/register-customer")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody RegisterCustomerNoClub registerCustomerNoClub) {
        CustomerDto newCustomer = orchestratorService.registerCustomerWithClub(registerCustomerNoClub);
        return ResponseEntity.ok(newCustomer);
    }










    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees in the system.")
    @ApiResponse(responseCode = "200", description = "List of employees returned successfully")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeServiceClient.getAllEmployees());
    }

    @Operation(summary = "Get a specific employee", description = "Returns the employee details.")
    @ApiResponse(responseCode = "200", description = "Customer returned successfully")
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeServiceClient.getEmployeeById(id));
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
    @PostMapping("/employees/register-employee")
    public ResponseEntity<EmployeeDto> registerEmployee(@RequestBody RegisterEmployeeDto registerEmployeeDto) {
        EmployeeDto employeeDto = employeeServiceClient.createEmployee(registerEmployeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    @Operation(summary = "Update an employee", description = "Updates an existing employee's details.")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully",
            content = @Content(schema = @Schema(implementation = EmployeeDto.class)))
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,
                                                      @RequestBody RegisterEmployeeDto updateEmployeeDto) {
        return ResponseEntity.ok(employeeServiceClient.updateEmployee(id, updateEmployeeDto));
    }

    @Operation(summary = "Assign a role to an employee", description = "Adds a role to an employee.")
    @ApiResponse(responseCode = "200", description = "Role added successfully")
    @PostMapping("/employees/{id}/roles")
    public ResponseEntity<Set<EmployeeRole>> addRole(@PathVariable("id") Long id,
                                                     @RequestBody EmployeeRole role) {
        return ResponseEntity.ok(employeeServiceClient.addRole(id, role));
    }

    @Operation(summary = "Remove a role from an employee", description = "Removes a specific role from an employee.")
    @ApiResponse(responseCode = "200", description = "Role removed successfully")
    @DeleteMapping("/employees/{id}/roles")
    public ResponseEntity<Set<EmployeeRole>> removeRole(@PathVariable("id") Long id,
                                                        @RequestBody EmployeeRole role) {
        return ResponseEntity.ok(employeeServiceClient.removeRole(id, role));
    }

    @Operation(summary = "Assign an expertise area to an employee", description = "Assigns an employee to a specific expertise area within a club.")
    @ApiResponse(responseCode = "200", description = "Expertise area assigned successfully")
    @PostMapping("/employees/{employeeId}/assign-expertise")
    public ResponseEntity<EmployeeDto> assignExpertiseArea(
            @PathVariable("employeeId") Long employeeId,
            @RequestParam("clubId") Long clubId,
            @RequestParam("expertiseAreaId") Long expertiseAreaId) {
        return ResponseEntity.ok(employeeServiceClient.assignExpertiseArea(employeeId, clubId, expertiseAreaId));
    }









    @Operation(summary = "Get all memberships", description = "Returns all membership details.")
    @ApiResponse(responseCode = "200", description = "List of memberships returned successfully")
    @GetMapping("/memberships")
    public ResponseEntity<List<MembershipDto>> getAllMemberships() {
        return ResponseEntity.ok(membershipServiceClient.getAllMemberships());
    }

    @Operation(summary = "Get an specific membership", description = "Returns the membership details.")
    @ApiResponse(responseCode = "200", description = "Membership returned successfully")
    @GetMapping("/memberships/{uuid}")
    public ResponseEntity<MembershipDto> getMembershipByUuid(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(membershipServiceClient.getMembershipById(uuid));
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