import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Employee } from '../employee-list/employee';
import { CommonService } from '../_services/common.service';


@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  private employeefrm: FormGroup;
  constructor(private commonService: CommonService, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.employeefrm = this.formBuilder.group({
      id: [],
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      department: new FormControl('', [Validators.required]),
    });

    const employeeId = localStorage.getItem('empId');

    if (employeeId) {
      this.commonService.getEmployeeById(employeeId).subscribe(response => {
        this.employeefrm.setValue(response);
      });
    }

  }

  submitForm() {
    this.commonService.saveEmployee(this.employeefrm.value).subscribe(d => {
      this.employeefrm.reset();
      console.log(d);
    });
  }

}
