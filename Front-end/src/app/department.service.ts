import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Department } from './department.model';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  private apiUrl = 'http://localhost:8080/api/department';

  constructor(private http: HttpClient) { }

  getDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>(`${this.apiUrl}/findAll`);
  }

  saveDepartment(department: Department): Observable<Department> {
    return this.http.post<Department>(`${this.apiUrl}/signup`, department);
  }

  updateDepartment(department: Department): Observable<Department> {
    return this.http.put<Department>(`${this.apiUrl}/editDepartment/${department.did}`, department);
  }

  deleteDepartment(did: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteDepartment/${did}`);
  }
}
