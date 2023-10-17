package com.SchoolProject2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.SchoolProject2.entity.Employee;
import com.SchoolProject2.exception.EmployeeNotFoundException;
import com.SchoolProject2.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/find/{eid}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int eid) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeById(eid);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/editEmployee/{eid}")
    public ResponseEntity<Employee> editEmployee(@PathVariable int eid, @Valid @RequestBody Employee employee)
            throws EmployeeNotFoundException {
        employee.setEid(eid);
        Employee updatedEmployee = employeeService.updateEmployee(eid, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/deleteEmployee/{eid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int eid) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(eid);
        return ResponseEntity.ok("Deleted Successfully..");
    }
}
