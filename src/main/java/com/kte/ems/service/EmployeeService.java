package com.kte.ems.service;

import com.kte.ems.dto.EmployeeRequest;
import com.kte.ems.dto.EmployeeResponse;
import com.kte.ems.dto.EmployeeUpdateRequest;
import com.kte.ems.entity.Department;
import com.kte.ems.entity.Employee;
import com.kte.ems.enums.EmploymentStatus;
import com.kte.ems.exception.DuplicateResourceException;
import com.kte.ems.exception.ResourceNotFoundException;
import com.kte.ems.mapper.EmployeeMapper;
import com.kte.ems.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        validateCreate(request);
        Department department = departmentService.findEntity(request.departmentId());
        Employee employee = EmployeeMapper.toEntity(request, department);
        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Transactional(readOnly = true)
    public EmployeeResponse findById(Long id) {
        return EmployeeMapper.toResponse(findEntity(id));
    }

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> search(String keyword, EmploymentStatus status, Long departmentId, Pageable pageable) {
        return employeeRepository.search(blankToNull(keyword), status, departmentId, pageable)
                .map(EmployeeMapper::toResponse);
    }

    @Transactional
    public EmployeeResponse update(Long id, EmployeeUpdateRequest request) {
        Employee employee = findEntity(id);
        if (employeeRepository.existsByEmailAndIdNot(request.email().trim().toLowerCase(), id)) {
            throw new DuplicateResourceException("Employee email already exists: " + request.email());
        }
        Department department = departmentService.findEntity(request.departmentId());
        EmployeeMapper.update(employee, request, department);
        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Transactional
    public void delete(Long id) {
        Employee employee = findEntity(id);
        employeeRepository.delete(employee);
    }

    private Employee findEntity(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    private void validateCreate(EmployeeRequest request) {
        if (employeeRepository.existsByEmployeeNumber(request.employeeNumber().trim().toUpperCase())) {
            throw new DuplicateResourceException("Employee number already exists: " + request.employeeNumber());
        }
        if (employeeRepository.existsByEmail(request.email().trim().toLowerCase())) {
            throw new DuplicateResourceException("Employee email already exists: " + request.email());
        }
    }

    private String blankToNull(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
