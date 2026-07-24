package com.cognizant.ems.service;

import com.cognizant.ems.entity.Department;
import com.cognizant.ems.entity.Employee;
import com.cognizant.ems.projection.EmployeeSummary;
import com.cognizant.ems.repository.DepartmentRepository;
import com.cognizant.ems.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Page<Employee> getAllEmployees(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            employee.setDepartment(dept);
        }
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(Long id, Employee empDetails) {
        return employeeRepository.findById(id).map(emp -> {
            emp.setName(empDetails.getName());
            emp.setEmail(empDetails.getEmail());
            emp.setSalary(empDetails.getSalary());
            if (empDetails.getDepartment() != null && empDetails.getDepartment().getId() != null) {
                Department dept = departmentRepository.findById(empDetails.getDepartment().getId())
                        .orElseThrow(() -> new RuntimeException("Department not found"));
                emp.setDepartment(dept);
            } else {
                emp.setDepartment(null);
            }
            return employeeRepository.save(emp);
        });
    }

    public boolean deleteEmployee(Long id) {
        return employeeRepository.findById(id).map(emp -> {
            employeeRepository.delete(emp);
            return true;
        }).orElse(false);
    }

    // Dynamic filtering using derived query method
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Named Query Execution 1: findByEmail
    public Employee getEmployeeByEmailNamed(String email) {
        return employeeRepository.findByEmailNamed(email);
    }

    // Named Query Execution 2: findBySalaryGreaterThan
    public List<Employee> getEmployeesWithSalaryGreaterThanNamed(Double salary) {
        return employeeRepository.findBySalaryGreaterThanNamed(salary);
    }

    // Paginated/Sorted Query on Department ID using @Query
    public Page<Employee> getEmployeesByDepartmentId(Long deptId, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findEmployeesByDepartmentId(deptId, pageable);
    }

    // Interface-based Projection Method
    public List<EmployeeSummary> getEmployeeSummaries() {
        return employeeRepository.findAllEmployeeSummaries();
    }

    // Paginated/Sorted Projection Search
    public Page<EmployeeSummary> getEmployeeSummariesByName(String name, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        // Pageable version of projections search
        List<EmployeeSummary> content = employeeRepository.findByNameContainingIgnoreCase(name, pageable);
        // Turn into a page if needed or just return list. Let's return list or handle pageable list.
        return new org.springframework.data.domain.PageImpl<>(content, pageable, content.size());
    }

    /**
     * Demonstrating Batch Processing
     * Saves a list of employees in batch. We can flush the entity manager periodically
     * to prevent out-of-memory errors and ensure statements are batch-executed.
     */
    @Transactional
    public List<Employee> saveEmployeesInBatch(List<Employee> employees) {
        List<Employee> savedEmployees = new ArrayList<>();
        int batchSize = 20; // Matches application.properties configuration
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            
            // Resolve department if exists
            if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
                Department dept = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
                employee.setDepartment(dept);
            }
            
            entityManager.persist(employee);
            savedEmployees.add(employee);
            
            if (i > 0 && i % batchSize == 0) {
                // Flush a batch of inserts and release memory
                entityManager.flush();
                entityManager.clear();
            }
        }
        return savedEmployees;
    }
}
