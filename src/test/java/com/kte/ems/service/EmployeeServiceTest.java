package com.kte.ems.service;

import com.kte.ems.dto.EmployeeRequest;
import com.kte.ems.entity.Department;
import com.kte.ems.enums.EmploymentStatus;
import com.kte.ems.enums.JobLevel;
import com.kte.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Test
    void createEmployeeShouldPersistWhenEmployeeNumberAndEmailAreUnique() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        DepartmentService departmentService = mock(DepartmentService.class);
        EmployeeService service = new EmployeeService(employeeRepository, departmentService);

        Department department = new Department();
        department.setId(1L);
        department.setCode("ENG");
        department.setName("Engineering");
        when(departmentService.findEntity(1L)).thenReturn(department);
        when(employeeRepository.existsByEmployeeNumber("EMP000001")).thenReturn(false);
        when(employeeRepository.existsByEmail("john.doe@example.com")).thenReturn(false);
        when(employeeRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeRequest request = new EmployeeRequest(
                "EMP000001", "John", "Doe", "john.doe@example.com", "+1-555-1000",
                EmploymentStatus.ACTIVE, JobLevel.L2_ENGINEER, "Java Developer",
                LocalDate.now().minusDays(10), BigDecimal.valueOf(95000), 1L
        );

        var response = service.create(request);

        assertThat(response.employeeNumber()).isEqualTo("EMP000001");
        assertThat(response.departmentCode()).isEqualTo("ENG");
        verify(employeeRepository, times(1)).save(any());
    }
}
