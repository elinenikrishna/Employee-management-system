package com.kte.ems.dto;

import com.kte.ems.enums.EmploymentStatus;
import com.kte.ems.enums.JobLevel;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeUpdateRequest(
        @NotBlank @Size(max = 80) String firstName,
        @NotBlank @Size(max = 80) String lastName,
        @NotBlank @Email @Size(max = 160) String email,
        @Size(max = 32) String phone,
        @NotNull EmploymentStatus status,
        @NotNull JobLevel jobLevel,
        @NotBlank @Size(max = 120) String jobTitle,
        @NotNull @PastOrPresent LocalDate hireDate,
        @PositiveOrZero BigDecimal salary,
        @NotNull Long departmentId
) {}
