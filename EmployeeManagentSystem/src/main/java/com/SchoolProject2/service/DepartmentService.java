package com.SchoolProject2.service;
import java.util.Optional;

import com.SchoolProject2.entity.Department;
import com.SchoolProject2.exception.DepartmentNotFoundException;
import com.SchoolProject2.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(int did) throws DepartmentNotFoundException {
        return departmentRepository.findById(did)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + did));
    }


    public Department updateDepartment(int did, Department department) throws DepartmentNotFoundException {
        if (departmentRepository.existsById(did)) {
            department.setDid(did);
            return departmentRepository.save(department);
        } else {
            throw new DepartmentNotFoundException("Department not found with id: " + did);
        }
    }

    public void deleteDepartment(int did) throws DepartmentNotFoundException {
        if (departmentRepository.existsById(did)) {
            departmentRepository.deleteById(did);
        } else {
            throw new DepartmentNotFoundException("Department not found with id: " + did);
        }
    }
}
