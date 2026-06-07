# API Design Standards

## API Versioning

All APIs use `/api/v1` to support future backward-compatible evolution.

## Response Style

The API returns DTOs instead of exposing JPA entities directly.

## Validation

Requests use Jakarta Bean Validation annotations such as `@NotBlank`, `@Email`, `@Size`, `@PastOrPresent`, and `@PositiveOrZero`.

## Error Handling

Errors are returned in a consistent JSON format:

```json
{
  "timestamp": "2026-06-07T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Request validation failed",
  "path": "/api/v1/employees",
  "fieldErrors": []
}
```
