import { Component, OnInit } from '@angular/core';
import { CommonService } from '../_services/common.service';
import { Employee } from './employee';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})

export class EmployeeListComponent implements OnInit {

  data: Employee[] = null;
  constructor(
    private commonservice: CommonService,
    private router: Router,
    private _toastr: ToastrService
  ) { }

  ngOnInit() {
    this.getEmpData();
  }

  getEmpData() {
    this.commonservice.getUsers().subscribe(response => {
      if (response) {
        this.data = response;
      }
    });
  }

  deleteEmployee(employee) {
    this.commonservice.removeEmployee(employee).subscribe(data => {
      this.getEmpData();
      this._toastr.success('Employee Deleted successfully');
    });
  }

  editEmployee(employee) {
    localStorage.setItem('empId', employee.id);
    this.router.navigateByUrl('/create');
  }

  addEmployee() {
    this.router.navigateByUrl('/create');
  }
}
