package com.kte.ems.repository;

import com.kte.ems.entity.Employee;
import com.kte.ems.enums.EmploymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    boolean existsByEmployeeNumber(String employeeNumber);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

    @Query("""
            select e from Employee e
            join fetch e.department d
            where (:keyword is null or lower(e.firstName) like lower(concat('%', :keyword, '%'))
                or lower(e.lastName) like lower(concat('%', :keyword, '%'))
                or lower(e.email) like lower(concat('%', :keyword, '%'))
                or lower(e.employeeNumber) like lower(concat('%', :keyword, '%')))
              and (:status is null or e.status = :status)
              and (:departmentId is null or d.id = :departmentId)
            """)
    Page<Employee> search(@Param("keyword") String keyword,
                          @Param("status") EmploymentStatus status,
                          @Param("departmentId") Long departmentId,
                          Pageable pageable);
}
