package com.SchoolProject2.service;

import com.SchoolProject2.entity.Employee;
import com.SchoolProject2.exception.EmployeeNotFoundException;
import com.SchoolProject2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    public Employee addEmployee(Employee employee) {
        // You can perform validation or business logic before saving the employee
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) throws EmployeeNotFoundException {
        // Retrieve the existing employee from the database
        Employee existingEmployee = getEmployeeById(id);
        
        // Update the properties of the existing employee with the new values
        existingEmployee.setEfname(updatedEmployee.getEfname());
        existingEmployee.setElname(updatedEmployee.getElname());
        existingEmployee.setEedu(updatedEmployee.getEedu());
        existingEmployee.setEemail(updatedEmployee.getEemail());
        existingEmployee.setEadd(updatedEmployee.getEadd());
        existingEmployee.setEphone(updatedEmployee.getEphone());

        // Save the updated employee back to the database
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        // Check if the employee exists in the database
        if (employeeRepository.existsById(id)) {
            // If it exists, delete the employee
            employeeRepository.deleteById(id);
        } else {
            // If the employee with the given ID does not exist, throw an exception
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
    }
}
