import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private _http: HttpClient) { }

  getDoctorList(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/doctors`);
  }

  getPatientList(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/patients`);
  }

  deactivatePatient(id: number): Observable<any> {
    return this._http.put<any>(environment.baseURL + environment.admin + `/deactivate`, id);
  }

  activatePatient(id: number): Observable<any> {
    return this._http.put<any>(environment.baseURL + environment.admin + `/activate`, id);
  }

  getConcerns(id: number): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/concerns/${id}`);
  }

  sendWarning(id: number): Observable<any> {
    return this._http.put<any>(environment.baseURL + environment.admin + `/warning`, id);
  }

  blockDoctor(id: number): Observable<any> {
    return this._http.put<any>(environment.baseURL + environment.admin + `/block`, JSON.parse(String(id)));
  }

  unblockDoctor(id: number): Observable<any> {
    return this._http.put<any>(environment.baseURL + environment.admin + `/unblock`, JSON.parse(String(id)));
  }

  getUsers(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/users`);
  }

  getLogs(id: number): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/logs/${id}`);
  }

  getStats(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/stats`);
  }

  getDoctorStats(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/doctorStats`);
  }

  getAppointmentStats(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/appointmentStats`);
  }

  getRatingStats(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/feedbackStats`);
  }

  getActivityStats(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/activity`);
  }

  getUserStats(): Observable<any> {
    return this._http.get<any>(environment.baseURL + environment.admin + `/userStats`);
  }
}
