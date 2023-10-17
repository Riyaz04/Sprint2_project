import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee.model';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employees: Employee[] = [];
  newEmployee: Employee = { eid: 0, efname: '', elname: '', eedu: '', eemail: '', eadd: '', ephone: 0 };

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.fetchEmployees();
  }

  fetchEmployees(): void {
    this.employeeService.getEmployees().subscribe(employees => {
      this.employees = employees;
    });
  }

  saveEmployee(): void {
    this.employeeService.addEmployee(this.newEmployee).subscribe(savedEmployee => {
      this.employees.push(savedEmployee);
      this.newEmployee = { eid: 0, efname: '', elname: '', eedu: '', eemail: '', eadd: '', ephone: 0 };
    });
  }

  updateEmployee(employee: Employee): void {
    this.employeeService.updateEmployee(employee.eid, employee).subscribe(updatedEmployee => {
      const index = this.employees.findIndex(e => e.eid === updatedEmployee.eid);
      if (index !== -1) {
        this.employees[index] = updatedEmployee;
      }
    });
  }

  deleteEmployee(eid: number): void {
    this.employeeService.deleteEmployee(eid).subscribe(() => {
      this.employees = this.employees.filter(e => e.eid !== eid);
    });
  }
}
