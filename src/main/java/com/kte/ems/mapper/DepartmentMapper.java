package com.kte.ems.mapper;

import com.kte.ems.dto.DepartmentRequest;
import com.kte.ems.dto.DepartmentResponse;
import com.kte.ems.entity.Department;

public final class DepartmentMapper {
    private DepartmentMapper() {}

    public static Department toEntity(DepartmentRequest request) {
        Department department = new Department();
        department.setCode(request.code().trim().toUpperCase());
        department.setName(request.name().trim());
        department.setCostCenter(request.costCenter());
        return department;
    }

    public static DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getCode(),
                department.getName(),
                department.getCostCenter(),
                department.getCreatedAt(),
                department.getUpdatedAt()
        );
    }
}
