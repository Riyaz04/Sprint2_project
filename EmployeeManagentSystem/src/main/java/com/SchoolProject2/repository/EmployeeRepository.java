package com.SchoolProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SchoolProject2.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Custom queries if needed
}