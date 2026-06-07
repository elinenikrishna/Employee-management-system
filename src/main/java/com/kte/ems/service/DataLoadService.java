package com.kte.ems.service;

import com.kte.ems.dto.DataLoadResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoadService {
    private final JdbcTemplate jdbcTemplate;

    public DataLoadService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void ensureBaseDepartments() {
        jdbcTemplate.update("""
                insert ignore into departments(code, name, cost_center, created_at, updated_at)
                values ('ENG','Engineering','CC-1001', utc_timestamp(), utc_timestamp()),
                       ('HR','Human Resources','CC-1002', utc_timestamp(), utc_timestamp()),
                       ('FIN','Finance','CC-1003', utc_timestamp(), utc_timestamp()),
                       ('OPS','Operations','CC-1004', utc_timestamp(), utc_timestamp()),
                       ('DATA','Data Platforms','CC-1005', utc_timestamp(), utc_timestamp())
                """);
    }

    public DataLoadResponse generateEmployees(int targetCount, int batchSize) {
        long start = System.currentTimeMillis();
        ensureBaseDepartments();
        long existing = jdbcTemplate.queryForObject("select count(*) from employees", Long.class);
        long toInsert = Math.max(targetCount - existing, 0);
        long inserted = 0;
        long sequenceStart = existing + 1;

        String sql = """
                insert into employees(employee_number, first_name, last_name, email, phone, status, job_level,
                                      job_title, hire_date, salary, department_id, version, created_at, updated_at)
                values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, utc_timestamp(), utc_timestamp())
                """;

        while (inserted < toInsert) {
            int currentBatch = (int) Math.min(batchSize, toInsert - inserted);
            List<Object[]> rows = new ArrayList<>(currentBatch);
            for (int i = 0; i < currentBatch; i++) {
                long n = sequenceStart + inserted + i;
                long deptId = (n % 5) + 1;
                rows.add(new Object[]{
                        "EMP" + String.format("%08d", n),
                        "First" + n,
                        "Last" + n,
                        "employee" + n + "@enterprise.example.com",
                        "+1-555-" + String.format("%07d", n % 10_000_000),
                        n % 17 == 0 ? "ON_LEAVE" : "ACTIVE",
                        n % 11 == 0 ? "L3_SENIOR_ENGINEER" : "L2_ENGINEER",
                        jobTitle(n),
                        Date.valueOf(LocalDate.now().minusDays(n % 3650)),
                        75000 + (n % 85000),
                        deptId
                });
            }
            jdbcTemplate.batchUpdate(sql, rows);
            inserted += currentBatch;
        }
        long duration = System.currentTimeMillis() - start;
        return new DataLoadResponse(targetCount, inserted, duration,
                "Data generation completed. Use MySQL indexes and pagination for large-result validation.");
    }

    private String jobTitle(long n) {
        return switch ((int) (n % 5)) {
            case 0 -> "Backend Engineer";
            case 1 -> "Java Developer";
            case 2 -> "Platform Engineer";
            case 3 -> "QA Automation Engineer";
            default -> "Data Engineer";
        };
    }
}
