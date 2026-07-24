package com.cognizant.hqljpql;

import com.cognizant.hqljpql.entity.Attempt;
import com.cognizant.hqljpql.entity.AttemptOption;
import com.cognizant.hqljpql.entity.AttemptQuestion;
import com.cognizant.hqljpql.entity.Department;
import com.cognizant.hqljpql.entity.Employee;
import com.cognizant.hqljpql.entity.Option;
import com.cognizant.hqljpql.entity.Question;
import com.cognizant.hqljpql.entity.Skill;
import com.cognizant.hqljpql.entity.User;
import com.cognizant.hqljpql.repository.AttemptOptionRepository;
import com.cognizant.hqljpql.repository.AttemptQuestionRepository;
import com.cognizant.hqljpql.repository.AttemptRepository;
import com.cognizant.hqljpql.repository.DepartmentRepository;
import com.cognizant.hqljpql.repository.EmployeeRepository;
import com.cognizant.hqljpql.repository.OptionRepository;
import com.cognizant.hqljpql.repository.QuestionRepository;
import com.cognizant.hqljpql.repository.SkillRepository;
import com.cognizant.hqljpql.repository.UserRepository;
import com.cognizant.hqljpql.service.EmployeeCriteriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HqlJpqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HqlJpqlDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            SkillRepository skillRepository,
            UserRepository userRepository,
            QuestionRepository questionRepository,
            OptionRepository optionRepository,
            AttemptRepository attemptRepository,
            AttemptQuestionRepository attemptQuestionRepository,
            AttemptOptionRepository attemptOptionRepository,
            EmployeeCriteriaService criteriaService) {
        return args -> {

            System.out.println("=== Part 2: HQL, JPQL, Native Query, Criteria Query Demo ===");
            System.out.println();

            // ---- Setup: Create Departments, Skills, Employees ----
            Department eng = departmentRepository.save(new Department("Engineering"));
            Department hr = departmentRepository.save(new Department("Human Resources"));

            Skill java = skillRepository.save(new Skill("Java"));
            Skill python = skillRepository.save(new Skill("Python"));

            Employee emp1 = new Employee("Alice", 80000.0, true, LocalDate.of(1990, 1, 15));
            emp1.setDepartment(eng);
            emp1.getSkills().add(java);
            emp1 = employeeRepository.save(emp1);

            Employee emp2 = new Employee("Bob", 50000.0, false, LocalDate.of(1995, 6, 20));
            emp2.setDepartment(hr);
            emp2.getSkills().add(python);
            emp2 = employeeRepository.save(emp2);

            Employee emp3 = new Employee("Carol", 90000.0, true, LocalDate.of(1988, 3, 10));
            emp3.setDepartment(eng);
            emp3.getSkills().add(java);
            emp3.getSkills().add(python);
            emp3 = employeeRepository.save(emp3);

            // ---- Exercise 2: HQL - Permanent Employees ----
            System.out.println("--- Exercise 2: HQL - All Permanent Employees ---");
            List<Employee> permanentEmps = employeeRepository.findPermanentEmployees();
            permanentEmps.forEach(e -> System.out.println("  " + e.getName() + " | Dept: " + e.getDepartment().getName()));
            System.out.println();

            System.out.println("--- Exercise 2: HQL JOIN FETCH - Permanent Employees with Department & Skills ---");
            List<Employee> permanentWithDetails = employeeRepository.findPermanentEmployeesWithDepartmentAndSkills();
            permanentWithDetails.forEach(e -> {
                System.out.println("  " + e.getName() + " | Dept: " + e.getDepartment().getName()
                        + " | Skills: " + e.getSkills().stream().map(Skill::getName).toList());
            });
            System.out.println();

            // ---- Exercise 3: Quiz Attempt HQL ----
            System.out.println("--- Exercise 3: Quiz Attempt HQL Setup ---");
            User user1 = userRepository.save(new User("john_doe", "john@test.com", "pass123"));
            Question q1 = questionRepository.save(new Question("What is JPA?", 5));
            Question q2 = questionRepository.save(new Question("What is HQL?", 3));

            Option opt1a = optionRepository.save(new Option(q1, "Java Persistence API", true));
            Option opt1b = optionRepository.save(new Option(q1, "Java Package API", false));
            Option opt2a = optionRepository.save(new Option(q2, "Hibernate Query Language", true));
            Option opt2b = optionRepository.save(new Option(q2, "High Query Layer", false));

            Attempt attempt1 = attemptRepository.save(new Attempt(user1, LocalDate.now(), 8));
            AttemptQuestion aq1 = attemptQuestionRepository.save(new AttemptQuestion(attempt1, q1));
            AttemptQuestion aq2 = attemptQuestionRepository.save(new AttemptQuestion(attempt1, q2));
            attemptOptionRepository.save(new AttemptOption(aq1, opt1a, true));
            attemptOptionRepository.save(new AttemptOption(aq1, opt1b, false));
            attemptOptionRepository.save(new AttemptOption(aq2, opt2a, true));
            attemptOptionRepository.save(new AttemptOption(aq2, opt2b, false));

            System.out.println("--- Exercise 3: Quiz Attempt HQL Result ---");
            List<Attempt> attempts = attemptRepository.findAllAttemptsWithDetails();
            for (Attempt a : attempts) {
                System.out.println("  Username: " + a.getUser().getUsername()
                        + " | Date: " + a.getAttemptDate()
                        + " | Total Marks: " + a.getTotalMarks());
                for (AttemptQuestion aq : a.getAttemptQuestions()) {
                    System.out.println("    Question: " + aq.getQuestion().getQuestionText()
                            + " | Marks: " + aq.getQuestion().getMarks());
                    for (AttemptOption ao : aq.getAttemptOptions()) {
                        System.out.println("      Option: " + ao.getOption().getOptionText()
                                + " | Correct: " + ao.getOption().isCorrect()
                                + " | Selected: " + ao.isSelected());
                    }
                }
            }
            System.out.println();

            // ---- Exercise 4: Average Salary HQL ----
            System.out.println("--- Exercise 4: Average Salary HQL ---");
            Double avgAll = employeeRepository.findAverageSalary();
            System.out.println("  Average salary (all employees): " + avgAll);

            Double avgEng = employeeRepository.findAverageSalaryByDepartment(eng.getId());
            System.out.println("  Average salary (Engineering dept): " + avgEng);
            System.out.println();

            // ---- Exercise 5: Native Query ----
            System.out.println("--- Exercise 5: Native Query - SELECT * FROM employee ---");
            List<Employee> nativeResult = employeeRepository.findAllNative();
            nativeResult.forEach(e -> System.out.println("  " + e));
            System.out.println();

            // ---- Exercise 6: Criteria Query ----
            System.out.println("--- Exercise 6: Criteria Query - Dynamic Search ---");

            System.out.println("  Search: permanent=true");
            List<Employee> result1 = criteriaService.searchEmployees(null, null, null, true);
            result1.forEach(e -> System.out.println("    " + e.getName() + " | " + e.getSalary()));

            System.out.println("  Search: name='al', minSalary=70000");
            List<Employee> result2 = criteriaService.searchEmployees("al", 70000.0, null, null);
            result2.forEach(e -> System.out.println("    " + e.getName() + " | " + e.getSalary()));

            System.out.println("  Search: departmentId=" + eng.getId() + ", permanent=true");
            List<Employee> result3 = criteriaService.searchEmployees(null, null, eng.getId(), true);
            result3.forEach(e -> System.out.println("    " + e.getName() + " | Dept: " + e.getDepartment().getName()));
            System.out.println();

            System.out.println("=== HQL JPQL Native Criteria Demo Complete ===");
        };
    }
}
