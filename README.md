# Employee Management System

Enterprise-grade Java Spring Boot backend for managing employee records, departments, statuses, job levels, and large-volume workforce data.

This project is built to reflect how backend services are structured in large organizations: layered architecture, REST APIs, validation, exception handling, database migrations, pagination, indexing, Dockerized infrastructure, API documentation, CI/CD workflow, and high-volume test data generation.

## Technology Stack

- Java 17
- Spring Boot 3.5.x
- Spring Web MVC
- Spring Data JPA / Hibernate
- MySQL 8.4
- Flyway database migrations
- Docker and Docker Compose
- OpenAPI / Swagger UI
- Spring Boot Actuator
- JUnit 5, Mockito, Testcontainers-ready structure
- GitHub Actions CI

## Architecture

```text
Controller Layer     REST endpoints, request validation, HTTP responses
Service Layer        Business rules, transactions, orchestration
Repository Layer     Spring Data JPA persistence
Entity Layer         Database mapping and domain model
DTO Layer            API request/response contracts
Exception Layer      Centralized error handling
Migration Layer      Flyway database versioning
```

## Features

- Employee CRUD APIs
- Department APIs
- Search employees by keyword, status, department
- Pagination and sorting for large data sets
- MySQL indexes for enterprise-scale read performance
- Global exception handling with structured error response
- Request validation using Jakarta Bean Validation
- Optimistic locking using JPA `@Version`
- Flyway migrations for repeatable database setup
- Dockerized MySQL and Spring Boot application
- Swagger UI API documentation
- 5 million employee record generation endpoint for realistic load simulation

## Run Locally

```bash
# Start MySQL and application
mvn clean package -DskipTests
docker compose up --build
```

Application URLs:

```text
API Base URL: http://localhost:8080/api/v1
Swagger UI:   http://localhost:8080/swagger-ui.html
Health:       http://localhost:8080/actuator/health
```

## Generate 5 Million Employee Records

The project does not store a huge SQL dump in GitHub. Instead, it provides a controlled data-loader endpoint for realistic MNC-scale testing.

```bash
./scripts/generate-5m-employees.sh
```

Or call directly:

```bash
curl -X POST http://localhost:8080/api/v1/admin/data-loader/employees \
  -H 'Content-Type: application/json' \
  -d '{"targetCount":5000000,"batchSize":10000}'
```

## Key APIs

### Create Department

```http
POST /api/v1/departments
```

### Create Employee

```http
POST /api/v1/employees
```

### Search Employees

```http
GET /api/v1/employees?keyword=java&status=ACTIVE&page=0&size=25&sortBy=lastName&direction=ASC
```

### Update Employee

```http
PUT /api/v1/employees/{id}
```

### Delete Employee

```http
DELETE /api/v1/employees/{id}
```

## Resume Bullet Version

- Designed and developed an enterprise-grade Employee Management System using Java, Spring Boot, REST APIs, MySQL, and Spring Data JPA.
- Implemented layered architecture with controller, service, repository, DTO, mapper, exception, and configuration layers.
- Built CRUD APIs for employee and department management with validation, pagination, sorting, and structured error handling.
- Designed MySQL schema with Flyway migrations, indexes, unique constraints, and optimistic locking for scalable data operations.
- Created a high-volume data generator to simulate up to 5 million employee records for realistic enterprise testing.
- Containerized the application using Docker Compose and documented API workflows using Swagger and Postman.

## Interview Explanation

This project simulates an enterprise HR backend system. I designed it with clean layered architecture, Spring Boot REST APIs, MySQL persistence, validation, global exception handling, Flyway migrations, pagination, indexes, Docker setup, and a 5-million-record data loader. The goal was to build a backend system that behaves like a real-world MNC service rather than a simple academic CRUD app.
