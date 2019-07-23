
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

    removeEmployee(employee) {
        return this.http.delete('external/delete/' + employee.id);
    }
}
