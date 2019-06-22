import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';


@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  private employeefrm: FormGroup;
  constructor() { }

  ngOnInit() {
    this.employeefrm = new FormGroup({
      empFirstName: new FormControl('', [Validators.required]),
      empLastName: new FormControl('', [Validators.required]),
      empDeptName: new FormControl('', [Validators.required]),
    });

  }

  submitForm() {
    console.log(this.employeefrm.value);
  }

}
