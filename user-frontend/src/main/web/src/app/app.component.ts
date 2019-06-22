import { CommonService } from './_services/common.service';
import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Employee-ui';
  appStatus = 'Down' ;
  users = '';

  constructor(
    private commonService: CommonService
  ) {
    // this.commonService.getServerUrl().subscribe(result => {
    //   console.log('baseUrl is, ', result);
    //   environment.serverUrl = result;
    // });
  }

  getUsers() {
    console.log('getAllUser');
    this.commonService.getUsers().subscribe(result => {
      console.log('User list ', result);
      this.users = result;
    });
  }


  getApiStatus() {
    console.log('Check API Status');
    this.commonService.getApiStatus().subscribe(result => {
      console.log('Status is, ', result);
      this.appStatus = JSON.stringify(result);
    });
  }
}
