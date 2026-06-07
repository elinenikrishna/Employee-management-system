# Architecture Overview

## Design Goal

The Employee Management System is designed as a production-style backend service for HR workforce data. The application follows layered architecture to separate API contracts, business logic, persistence, and database concerns.

## Layers

1. Controller Layer
   - Handles REST endpoints.
   - Validates incoming request payloads.
   - Returns clean response DTOs.

2. Service Layer
   - Owns business logic and transaction boundaries.
   - Validates duplicate employees and department existence.
   - Coordinates repository calls.

3. Repository Layer
   - Uses Spring Data JPA.
   - Provides search and persistence operations.

4. Entity Layer
   - Represents database tables.
   - Includes indexes, constraints, relationships, and optimistic locking.

5. Exception Layer
   - Converts technical errors into consistent API error responses.

## Scalability Decisions

- Pagination is enforced with a maximum page size.
- Indexes are added on employee number, email, department/status, and last name.
- Large test data is generated through batch inserts, not committed as huge files.
- Hibernate batching is enabled for bulk insert efficiency.
- MySQL `rewriteBatchedStatements=true` is enabled for large insert throughput.

## Production Readiness

- Flyway controls schema migrations.
- Actuator exposes health and metrics endpoints.
- Docker Compose provides local infrastructure.
- GitHub Actions provides CI build/test workflow.
- OpenAPI exposes live API documentation.
