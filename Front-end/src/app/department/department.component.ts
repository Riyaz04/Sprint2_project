import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '../department.service';
import { Department } from '../department.model';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {
  departments: Department[] = [];
  newDepartment: Department = { did: 0, dname: '', demail: '', dphone: 0, designation: '' };

  constructor(private departmentService: DepartmentService) { }

  ngOnInit(): void {
    this.fetchDepartments();
  }

  fetchDepartments(): void {
    this.departmentService.getDepartments().subscribe(departments => {
      this.departments = departments;
    });
  }

  saveDepartment(): void {
    this.departmentService.saveDepartment(this.newDepartment).subscribe(savedDepartment => {
      this.departments.push(savedDepartment);
      this.newDepartment = { did: 0, dname: '', demail: '', dphone: 0, designation: '' };
    });
  }

  updateDepartment(department: Department): void {
    this.departmentService.updateDepartment(department).subscribe(updatedDepartment => {
      const index = this.departments.findIndex(d => d.did === updatedDepartment.did);
      if (index !== -1) {
        this.departments[index] = updatedDepartment;
      }
    });
  }

  deleteDepartment(did: number): void {
    this.departmentService.deleteDepartment(did).subscribe(() => {
      this.departments = this.departments.filter(d => d.did !== did);
    });
  }
}
