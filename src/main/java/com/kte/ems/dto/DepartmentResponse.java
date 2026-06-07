package com.kte.ems.dto;

import java.time.Instant;

public record DepartmentResponse(
        Long id,
        String code,
        String name,
        String costCenter,
        Instant createdAt,
        Instant updatedAt
) {}
