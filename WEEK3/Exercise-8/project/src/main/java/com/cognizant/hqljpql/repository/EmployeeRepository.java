package com.cognizant.hqljpql.repository;

import com.cognizant.hqljpql.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Exercise 2: Basic HQL - Retrieve all permanent employees
    @Query("SELECT e FROM Employee e WHERE e.permanent = true")
    List<Employee> findPermanentEmployees();

    // Exercise 2: Optimized HQL - JOIN FETCH to eagerly load Department and Skills
    @Query("SELECT DISTINCT e FROM Employee e JOIN FETCH e.department JOIN FETCH e.skills WHERE e.permanent = true")
    List<Employee> findPermanentEmployeesWithDepartmentAndSkills();

    // Exercise 4: AVG salary of all employees
    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double findAverageSalary();

    // Exercise 4: AVG salary of a specific department using @Param
    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :departmentId")
    Double findAverageSalaryByDepartment(@Param("departmentId") Long departmentId);

    // Exercise 5: Native Query - all employees
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> findAllNative();
}
