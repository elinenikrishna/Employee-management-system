# Database Design

## Tables

### departments

Stores department metadata such as department code, name, and cost center.

### employees

Stores employee profile data, job status, job level, department reference, salary, and audit timestamps.

## Index Strategy

- `employee_number`: unique lookup by enterprise employee ID.
- `email`: unique lookup and duplicate prevention.
- `(department_id, status)`: high-volume filtered searches.
- `last_name`: common employee directory search.
- `(last_name, first_name)`: name-based pagination and sorting.

## Large Data Strategy

The project supports generation of up to 5 million rows using the local data-loader API. This better represents enterprise-scale data without making the Git repository slow or bloated.
