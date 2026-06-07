package com.kte.ems.controller;

import com.kte.ems.dto.EmployeeRequest;
import com.kte.ems.dto.EmployeeResponse;
import com.kte.ems.dto.EmployeeUpdateRequest;
import com.kte.ems.enums.EmploymentStatus;
import com.kte.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private static final int MAX_PAGE_SIZE = 200;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@Valid @RequestBody EmployeeRequest request) {
        return employeeService.create(request);
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    public Page<EmployeeResponse> search(@RequestParam(required = false) String keyword,
                                         @RequestParam(required = false) EmploymentStatus status,
                                         @RequestParam(required = false) Long departmentId,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "25") int size,
                                         @RequestParam(defaultValue = "lastName") String sortBy,
                                         @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        int safeSize = Math.min(Math.max(size, 1), MAX_PAGE_SIZE);
        return employeeService.search(keyword, status, departmentId, PageRequest.of(page, safeSize, direction, sortBy));
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest request) {
        return employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
