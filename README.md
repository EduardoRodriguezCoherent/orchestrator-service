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

- **GET** `/orchestrator/clubs`  
  Retrieve all clubs in the database.
- **GET** `/orchestrator/customers`  
  Retrieve all customers in the database.
- **GET** `/orchestrator/employees`  
  Retrieve all employees in the database.
- **GET** `/orchestrator/memberships`  
  Retrieve all memberships in the database.

- **POST** `/orchestrator/register-customer`  
  Register a new customer.
- **POST** `/orchestrator/register-employee`  
  Register a new employee.

- **PUT** `/memberships/{uuid}/upgrade-membership`  
  Upgrade a membership by using the UUID.
- **PUT** `/memberships/{uuid}/downgrade-membership`  
  Downgrade a membership by using the UUID.

### **Swagger Documentation**
You can find the swagger documentation in the following link:
http://localhost:8077/swagger-ui/index.html

### **Troubleshooting**
1. Ensure that the Discovery Service is running before starting the Orchestrator service.
2. Ensure all microservices are running before starting the orchestrator-service
3. Ensure all a Kafka instance is running and well configured.