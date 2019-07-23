import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { CommonService } from '../_services/common.service';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  private employeefrm: FormGroup;
  private employeeId;
  constructor(
    private commonService: CommonService,
    private formBuilder: FormBuilder,
    private _toastr: ToastrService
  ) { }

  ngOnInit() {
    this.employeefrm = this.formBuilder.group({
      id: [],
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      department: new FormControl('', [Validators.required]),
    });
    this.employeeId = localStorage.getItem('empId');
    if (this.employeeId) {
      this.commonService.getEmployeeById(this.employeeId).subscribe(response => {
        this.employeefrm.setValue(response);
      });
    }
  }

  submitForm() {
    this.commonService.saveEmployee(this.employeefrm.value).subscribe(d => {
      this.employeefrm.reset();
      if (this.employeeId) {
        this._toastr.success('Employee Updated successfully');
      } else {
        this._toastr.success('Employee Created successfully');
      }
    });
  }

}
