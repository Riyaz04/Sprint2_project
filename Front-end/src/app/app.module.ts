import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; // Import FormsModule

import { AppComponent } from './app.component';
import { DepartmentComponent } from './department/department.component';
import { EmployeeComponent } from './employee/employee.component';

@NgModule({
  declarations: [
    AppComponent,
    DepartmentComponent,
    EmployeeComponent
    // Add other components here
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule // Import FormsModule here
    // Add other modules here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
