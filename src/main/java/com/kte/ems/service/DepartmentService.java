package com.kte.ems.service;

import com.kte.ems.dto.DepartmentRequest;
import com.kte.ems.dto.DepartmentResponse;
import com.kte.ems.entity.Department;
import com.kte.ems.exception.DuplicateResourceException;
import com.kte.ems.exception.ResourceNotFoundException;
import com.kte.ems.mapper.DepartmentMapper;
import com.kte.ems.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public DepartmentResponse create(DepartmentRequest request) {
        if (departmentRepository.existsByCode(request.code().trim().toUpperCase())) {
            throw new DuplicateResourceException("Department code already exists: " + request.code());
        }
        return DepartmentMapper.toResponse(departmentRepository.save(DepartmentMapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll().stream().map(DepartmentMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Department findEntity(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }
}
