package com.kte.ems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kte.ems.dto.EmployeeRequest;
import com.kte.ems.enums.EmploymentStatus;
import com.kte.ems.enums.JobLevel;
import com.kte.ems.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockitoBean EmployeeService employeeService;

    @Test
    void searchEmployeesShouldReturnOk() throws Exception {
        when(employeeService.search(any(), any(), any(), any())).thenReturn(new PageImpl<>(List.of()));
        mockMvc.perform(get("/api/v1/employees")
                        .param("page", "0")
                        .param("size", "25"))
                .andExpect(status().isOk());
    }
}
