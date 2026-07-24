package com.cognizant.entitymapping;

import com.cognizant.entitymapping.entity.Department;
import com.cognizant.entitymapping.entity.Employee;
import com.cognizant.entitymapping.entity.Skill;
import com.cognizant.entitymapping.service.DepartmentService;
import com.cognizant.entitymapping.service.EmployeeService;
import com.cognizant.entitymapping.service.SkillService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class EntityMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntityMappingApplication.class, args);
    }

    @Bean
    CommandLineRunner run(EmployeeService employeeService,
                          DepartmentService departmentService,
                          SkillService skillService) {
        return args -> {

            System.out.println("=== Part 1 Exercises 3-6: Entity Mapping Demo ===");
            System.out.println();

            // --- Exercise 3 & 4: Create Departments ---
            System.out.println("--- Creating Departments (Exercise 3 & 4) ---");
            Department engineering = departmentService.save(new Department("Engineering"));
            Department hr = departmentService.save(new Department("Human Resources"));
            System.out.println("Saved: " + engineering);
            System.out.println("Saved: " + hr);
            System.out.println();

            // --- Exercise 6: Create Skills ---
            System.out.println("--- Creating Skills (Exercise 6) ---");
            Skill java = skillService.save(new Skill("Java"));
            Skill python = skillService.save(new Skill("Python"));
            Skill sql = skillService.save(new Skill("SQL"));
            System.out.println("Saved: " + java);
            System.out.println("Saved: " + python);
            System.out.println("Saved: " + sql);
            System.out.println();

            // --- Exercise 4: Add Employees with Department (ManyToOne) ---
            System.out.println("--- Test: Add Employee (Exercise 4) ---");
            Employee emp1 = new Employee("Alice Smith", 75000.0, true, LocalDate.of(1990, 5, 15));
            emp1.setDepartment(engineering);
            emp1.getSkills().add(java);
            emp1.getSkills().add(sql);
            emp1 = employeeService.save(emp1);
            System.out.println("Saved: " + emp1);

            Employee emp2 = new Employee("Bob Jones", 55000.0, false, LocalDate.of(1995, 8, 20));
            emp2.setDepartment(hr);
            emp2.getSkills().add(python);
            emp2 = employeeService.save(emp2);
            System.out.println("Saved: " + emp2);
            System.out.println();

            // --- Exercise 4: Get Employee ---
            System.out.println("--- Test: Get Employee by ID (Exercise 4) ---");
            Employee found = employeeService.get(emp1.getId()).orElseThrow();
            System.out.println("Found: " + found);
            System.out.println("  Department: " + found.getDepartment().getName());
            System.out.println("  Skills: " + found.getSkills().stream().map(Skill::getName).toList());
            System.out.println();

            // --- Exercise 4: Update Employee ---
            System.out.println("--- Test: Update Employee (Exercise 4) ---");
            found.setSalary(80000.0);
            found.setPermanent(true);
            Employee updated = employeeService.save(found);
            System.out.println("Updated: " + updated);
            System.out.println();

            // --- Exercise 5: Get Department with Employees (OneToMany EAGER) ---
            System.out.println("--- Test: Department with Employees (Exercise 5 - EAGER) ---");
            Department dept = departmentService.get(engineering.getId()).orElseThrow();
            System.out.println("Department: " + dept.getName());
            System.out.println("  Employees: " + dept.getEmployees().stream().map(Employee::getName).toList());
            System.out.println();

            // --- Exercise 6: Employee with Skills (ManyToMany EAGER) ---
            System.out.println("--- Test: Employee with Skills (Exercise 6 - ManyToMany) ---");
            Employee empWithSkills = employeeService.get(emp1.getId()).orElseThrow();
            System.out.println("Employee: " + empWithSkills.getName());
            System.out.println("  Skills: " + empWithSkills.getSkills().stream().map(Skill::getName).toList());

            // Add a new skill to existing employee
            empWithSkills.getSkills().add(python);
            employeeService.save(empWithSkills);
            System.out.println("  After adding Python - Skills: " +
                    employeeService.get(emp1.getId()).orElseThrow()
                            .getSkills().stream().map(Skill::getName).toList());
            System.out.println();

            System.out.println("=== Entity Mapping Demo Complete ===");
        };
    }
}
