package com.cognizant.ems.controller;

import com.cognizant.ems.entity.Employee;
import com.cognizant.ems.projection.EmployeeSummary;
import com.cognizant.ems.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CRUD - Read All (with Pagination and Sorting)
    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size, sortBy, sortDir));
    }

    // CRUD - Read One
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CRUD - Create
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee created = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // CRUD - Update
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CRUD - Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Query Methods (Derived Query)
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchEmployeesByName(name));
    }

    // Named Query Endpoint 1 (by Email)
    @GetMapping("/named/by-email")
    public ResponseEntity<Employee> getByEmailNamed(@RequestParam String email) {
        Employee emp = employeeService.getEmployeeByEmailNamed(email);
        if (emp != null) {
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.notFound().build();
    }

    // Named Query Endpoint 2 (Salary greater than)
    @GetMapping("/named/by-salary")
    public ResponseEntity<List<Employee>> getBySalaryNamed(@RequestParam Double salary) {
        return ResponseEntity.ok(employeeService.getEmployeesWithSalaryGreaterThanNamed(salary));
    }

    // Custom @Query Endpoint with dynamic pagination and sorting on Department
    @GetMapping("/department/{deptId}")
    public ResponseEntity<Page<Employee>> getEmployeesByDepartment(
            @PathVariable Long deptId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartmentId(deptId, page, size, sortBy, sortDir));
    }

    // Projection Endpoint: EmployeeSummaries (returns interface-based projection)
    @GetMapping("/summaries")
    public ResponseEntity<List<EmployeeSummary>> getEmployeeSummaries() {
        return ResponseEntity.ok(employeeService.getEmployeeSummaries());
    }

    // Paginated/Sorted Projection Search
    @GetMapping("/summaries/search")
    public ResponseEntity<Page<EmployeeSummary>> getEmployeeSummariesByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(employeeService.getEmployeeSummariesByName(name, page, size, sortBy, sortDir));
    }

    // Hibernate Batch Processing Demo Endpoint
    @PostMapping("/batch")
    public ResponseEntity<List<Employee>> createEmployeesBatch(@RequestBody List<Employee> employees) {
        List<Employee> saved = employeeService.saveEmployeesInBatch(employees);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
