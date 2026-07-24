package com.cognizant.ems.service;

import com.cognizant.ems.entity.Department;
import com.cognizant.ems.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> updateDepartment(Long id, Department deptDetails) {
        return departmentRepository.findById(id).map(dept -> {
            dept.setName(deptDetails.getName());
            return departmentRepository.save(dept);
        });
    }

    public boolean deleteDepartment(Long id) {
        return departmentRepository.findById(id).map(dept -> {
            departmentRepository.delete(dept);
            return true;
        }).orElse(false);
    }
}
