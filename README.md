# Orchestrator Service

Microservice part of Gym Management System, this service is the entry point of the application,
it contains endpoints to register new employees, customers and clubs, as well as update memberships.
All the interactions with the systems are handled here, you don't need to call any other separated micorservice.

## Running Locally

### **Prerequisites**
This service should be the last one to be started. In order to run the application locally, 
you have the following:

- **Java 17+** – Ensure you have Java Development Kit (JDK) installed.
- **Maven** – To build and run the project.

## Setup Instructions

### 1. Configure Application Properties
Configure and allow discovery for this service and also set properties to load balancer, this component allows calls
to different instances of the sme service distributing the load.

```properties
# Eureka Client Configuration
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true

ribbon.ReadTimeout=5000
ribbon.ConnectTimeout=3000
ribbon.MaxAutoRetries=3
ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RoundRobinRule
```
### 2. Start the Discovery Service
### 3. Start the Gym Club Service
### 5. Start the Customer Service
### 6. Start the Membership Service
### 7. Start the Notification Service   
7.1 Ensure you have kafka instance up and running.  
7.2 Start notification-service.
### 8. Start the Employee Service

### 9. Start the Orchestrator Service
Once the all the other microservices and Kafka instance ave started and are running. Start the orchestrator-service.

```bash
cd path/to/orchestrator-service
```
Run the following command to build and start the application:

```bash
mvn spring-boot:run
```
This will start the Orchestrator Service, which should now be available on http://localhost:8077 (or any configured port).

### **Orchestrator Service API Endpoints**

  #### ***Gym Clubs*** 
- **GET** `/orchestrator/clubs`  
  Retrieve all clubs in the database.  
- **GET** `/orchestrator/clubs/{id}`  
  Retrieve details of a specific club by its ID.  
- **POST** `/orchestrator/clubs/create-club`  
  Create a new club and return the created club details.
- **PUT** `/orchestrator/clubs/update-club`  
  Update an existing club and return the updated details.

  #### ***Customers***
- **GET** `/orchestrator/customers`  
  Retrieve all customers in the database.
- **GET** `/orchestrator/customers/{id}`  
  Retrieve details of a specific customer by their ID.
- **POST** `/orchestrator/customers/register-customer`  
  Register a new customer without assigning them to a club.

  #### ***Employees***
- **GET** `/orchestrator/employees`  
  Retrieve all employees in the database.
- **GET** `/orchestrator/employees/{id}`  
  Retrieve details of a specific employee by their ID.
- **POST** `/orchestrator/employees/register-employee`  
  Create a new employee record and return the created employee details.
- **PUT** `/orchestrator/employees/{id}`  
  Update an existing employee’s details and return the updated employee.
- **POST** `/orchestrator/employees/{id}/roles`  
  Assign a role to an employee.
- **DELETE** `/orchestrator/employees/{id}/roles`  
  Remove a role from an employee.
- **POST** `/orchestrator/employees/{employeeId}/assign-expertise`  
  Assign an expertise area to an employee within a gym club.

  #### ***Memberships***
- **GET** `/orchestrator/memberships`  
  Retrieve all memberships in the database.
- **GET** `/orchestrator/memberships/{uuid}`  
  Retrieve details of a specific membership by its UUID.
- **PUT** `/orchestrator/memberships/{uuid}/upgrade-membership`  
  Upgrade a customer's membership to GOLD.
- **PUT** `/orchestrator/memberships/{uuid}/downgrade-membership`  
  Downgrade a customer's membership to BASIC.

  
### **Swagger Documentation**
You can find the swagger documentation in the following link:
http://localhost:8077/swagger-ui/index.html

### **Troubleshooting**
1. Ensure that the Discovery Service is running before starting the Orchestrator service.
2. Ensure all microservices are running before starting the orchestrator-service
3. Ensure all a Kafka instance is running and well configured.