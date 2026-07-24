package com.cognizant.hqljpql.service;

import com.cognizant.hqljpql.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 6 - Criteria Query Service
 *
 * Criteria API Components:
 *
 * CriteriaBuilder  - Factory for creating query objects, predicates, and expressions.
 *                    Obtained from EntityManager.getCriteriaBuilder().
 *
 * CriteriaQuery    - Represents the query definition. You specify SELECT, FROM, WHERE here.
 *                    Created via CriteriaBuilder.createQuery(ResultType.class).
 *
 * Root             - Represents the root entity in the FROM clause.
 *                    Created via CriteriaQuery.from(EntityClass.class).
 *
 * TypedQuery       - A compiled, executable query with a known result type.
 *                    Created via EntityManager.createQuery(CriteriaQuery).
 *                    Used to call .getResultList() or .getSingleResult().
 */
@Service
public class EmployeeCriteriaService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Dynamic search for employees with optional filters.
     * Any combination of name, minSalary, departmentId, permanent can be provided.
     * Null values are ignored.
     */
    public List<Employee> searchEmployees(String name, Double minSalary, Long departmentId, Boolean permanent) {

        // Step 1: Get CriteriaBuilder from EntityManager
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Step 2: Create CriteriaQuery specifying the result type
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        // Step 3: Define the root entity (FROM clause)
        Root<Employee> root = cq.from(Employee.class);

        // Step 4: Build predicates dynamically
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (minSalary != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("salary"), minSalary));
        }

        if (departmentId != null) {
            predicates.add(cb.equal(root.get("department").get("id"), departmentId));
        }

        if (permanent != null) {
            predicates.add(cb.equal(root.get("permanent"), permanent));
        }

        // Step 5: Combine predicates with AND and set in WHERE clause
        cq.where(predicates.toArray(new Predicate[0]));

        // Step 6: Create TypedQuery and execute
        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
