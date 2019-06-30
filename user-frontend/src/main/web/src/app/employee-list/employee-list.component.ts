import { Component, OnInit } from '@angular/core';
import { CommonService } from '../_services/common.service';
import { Employee } from './employee';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {


  data: Employee[] = null;
  constructor(private commonservice: CommonService, private router: Router) { }

  ngOnInit() {

    this.commonservice.getUsers().subscribe(response => {
      if (response) {
       this.data = response;
      }
      console.log(this.data);
    });
  }

  deleteEmployee(employee) {
    console.log(employee, 'delete');
    this.commonservice.removeEmployee(employee);
  }

  editEmployee(employee) {
    localStorage.setItem('empId', employee.id);
    this.router.navigateByUrl('/create');
  }

  addEmployee() {
    this.router.navigateByUrl('/create');
  }

}
