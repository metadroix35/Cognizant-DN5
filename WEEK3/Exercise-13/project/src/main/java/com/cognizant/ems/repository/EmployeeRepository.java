package com.cognizant.ems.repository;

import com.cognizant.ems.entity.Employee;
import com.cognizant.ems.projection.EmployeeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // 1. Query Method (Derived)
    List<Employee> findByNameContainingIgnoreCase(String name);

    // 2. Named Query (defined in Employee entity as Employee.findByEmailNamed)
    Employee findByEmailNamed(@Param("email") String email);

    // 3. Named Query (defined in Employee entity as Employee.findBySalaryGreaterThanNamed)
    List<Employee> findBySalaryGreaterThanNamed(@Param("salary") Double salary);

    // 4. @Query (custom query) with pagination/sorting support
    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId")
    Page<Employee> findEmployeesByDepartmentId(@Param("deptId") Long deptId, Pageable pageable);

    // 5. Query returning Projection
    @Query("SELECT e FROM Employee e")
    List<EmployeeSummary> findAllEmployeeSummaries();

    // 6. Query returning Projection with criteria/name search
    List<EmployeeSummary> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
