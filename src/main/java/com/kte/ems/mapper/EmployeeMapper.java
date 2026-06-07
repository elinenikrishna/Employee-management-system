package com.kte.ems.mapper;

import com.kte.ems.dto.EmployeeRequest;
import com.kte.ems.dto.EmployeeResponse;
import com.kte.ems.dto.EmployeeUpdateRequest;
import com.kte.ems.entity.Department;
import com.kte.ems.entity.Employee;

public final class EmployeeMapper {
    private EmployeeMapper() {}

    public static Employee toEntity(EmployeeRequest request, Department department) {
        Employee employee = new Employee();
        employee.setEmployeeNumber(request.employeeNumber().trim().toUpperCase());
        apply(employee, request.firstName(), request.lastName(), request.email(), request.phone(), request.status(),
                request.jobLevel(), request.jobTitle(), request.hireDate(), request.salary(), department);
        return employee;
    }

    public static void update(Employee employee, EmployeeUpdateRequest request, Department department) {
        apply(employee, request.firstName(), request.lastName(), request.email(), request.phone(), request.status(),
                request.jobLevel(), request.jobTitle(), request.hireDate(), request.salary(), department);
    }

    private static void apply(Employee employee, String firstName, String lastName, String email, String phone,
                              com.kte.ems.enums.EmploymentStatus status,
                              com.kte.ems.enums.JobLevel jobLevel,
                              String jobTitle,
                              java.time.LocalDate hireDate,
                              java.math.BigDecimal salary,
                              Department department) {
        employee.setFirstName(firstName.trim());
        employee.setLastName(lastName.trim());
        employee.setEmail(email.trim().toLowerCase());
        employee.setPhone(phone);
        employee.setStatus(status);
        employee.setJobLevel(jobLevel);
        employee.setJobTitle(jobTitle.trim());
        employee.setHireDate(hireDate);
        employee.setSalary(salary);
        employee.setDepartment(department);
    }

    public static EmployeeResponse toResponse(Employee employee) {
        Department department = employee.getDepartment();
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeNumber(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getStatus(),
                employee.getJobLevel(),
                employee.getJobTitle(),
                employee.getHireDate(),
                employee.getSalary(),
                department.getId(),
                department.getCode(),
                department.getName(),
                employee.getVersion(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }
}
