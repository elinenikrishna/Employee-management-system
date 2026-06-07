package com.kte.ems.dto;

import com.kte.ems.enums.EmploymentStatus;

public record EmployeeSearchCriteria(
        String keyword,
        EmploymentStatus status,
        Long departmentId
) {}
