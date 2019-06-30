
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Subject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CommonService {
    constructor(private http: HttpClient) { }

    // getServerUrl() {
    //     return this.http.get('external/config', { responseType: 'text' });
    // }

    getApiStatus() {
        return this.http.get<any>('external/status');
    }

    getUsers() {
        return this.http.get<any>('external/employees');
    }

    getEmployeeById(id) {
        return this.http.get<any>('external/employee/' + id);
    }

    saveEmployee(employee) {
        return this.http.post('external/save', employee);
    }

    removeEmployee( employee) {
        console.log(employee.id, 'delete service');
        return this.http.delete('external/delete/' + employee.id).subscribe(data => {
            console.log(data);
        });
    }
}
