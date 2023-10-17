package com.SchoolProject2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ... (other imports)

import com.SchoolProject2.entity.Department; // Change import
import com.SchoolProject2.exception.DepartmentNotFoundException;
import com.SchoolProject2.service.DepartmentService; // Change import

import jakarta.validation.Valid;

//@Configuration(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/signup")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Department>> getAllDepartmentInfo() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/find/{did}")
    public ResponseEntity<Department> getDepartment(@PathVariable int did) throws DepartmentNotFoundException {
        Department department = departmentService.getDepartmentById(did);
        return ResponseEntity.ok(department);
    }

    @PutMapping("/editDepartment/{did}")
    public ResponseEntity<Department> editDepartment(@PathVariable int did, @RequestBody Department department)
            throws DepartmentNotFoundException {
        Department updatedDepartment = departmentService.updateDepartment(did, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/deleteDepartment/{did}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int did) throws DepartmentNotFoundException {
        departmentService.deleteDepartment(did);
        return ResponseEntity.ok("Deleted Successfully..");
    }
}
