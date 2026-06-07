package com.kte.ems.dto;

import com.kte.ems.enums.EmploymentStatus;
import com.kte.ems.enums.JobLevel;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record EmployeeResponse(
        Long id,
        String employeeNumber,
        String firstName,
        String lastName,
        String email,
        String phone,
        EmploymentStatus status,
        JobLevel jobLevel,
        String jobTitle,
        LocalDate hireDate,
        BigDecimal salary,
        Long departmentId,
        String departmentCode,
        String departmentName,
        Long version,
        Instant createdAt,
        Instant updatedAt
) {}
