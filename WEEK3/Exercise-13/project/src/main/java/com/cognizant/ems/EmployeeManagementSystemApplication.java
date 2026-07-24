package com.cognizant.ems;

import com.cognizant.ems.entity.Department;
import com.cognizant.ems.entity.Employee;
import com.cognizant.ems.service.DepartmentService;
import com.cognizant.ems.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(EmployeeService employeeService, DepartmentService departmentService) {
        return args -> {
            System.out.println("=== Initializing EMS Data ===");

            // Create Departments
            Department tech = departmentService.createDepartment(new Department("IT Services"));
            Department hr = departmentService.createDepartment(new Department("Human Resources"));
            Department sales = departmentService.createDepartment(new Department("Sales & Marketing"));

            // Create Employees (standard)
            Employee emp1 = new Employee();
            emp1.setName("John Doe");
            emp1.setEmail("john.doe@ems.com");
            emp1.setSalary(75000.0);
            emp1.setDepartment(tech);
            employeeService.createEmployee(emp1);

            Employee emp2 = new Employee();
            emp2.setName("Jane Smith");
            emp2.setEmail("jane.smith@ems.com");
            emp2.setSalary(85000.0);
            emp2.setDepartment(tech);
            employeeService.createEmployee(emp2);

            Employee emp3 = new Employee();
            emp3.setName("Emily Watson");
            emp3.setEmail("emily.watson@ems.com");
            emp3.setSalary(60000.0);
            emp3.setDepartment(hr);
            employeeService.createEmployee(emp3);

            // Let's demo Batch Processing
            System.out.println("=== Testing Batch Insert Process ===");
            List<Employee> batchEmployees = new ArrayList<>();
            for (int i = 1; i <= 30; i++) {
                Employee emp = new Employee();
                emp.setName("Batch Worker " + i);
                emp.setEmail("worker" + i + "@ems.com");
                emp.setSalary(50000.0 + (i * 100));
                emp.setDepartment(sales);
                batchEmployees.add(emp);
            }
            employeeService.saveEmployeesInBatch(batchEmployees);

            System.out.println("=== EMS Data Initialization Complete ===");
        };
    }
}
