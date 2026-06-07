package com.kte.ems.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI employeeManagementOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Employee Management System API")
                .version("1.0.0")
                .description("Enterprise-grade employee management backend with REST APIs, MySQL, validation, pagination, exception handling, Docker, and production-style documentation.")
                .contact(new Contact().name("Krishna T E").email("krishnaelineni@gmail.com")));
    }
}
