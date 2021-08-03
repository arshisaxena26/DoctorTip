import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DoctorService {
  constructor(private _httpClient: HttpClient) { }

  viewDoctorAppointmentent(): Observable<any> {
    return this._httpClient.get<any>(environment.baseURL + environment.doctor + `/getAppointment`);
    // throw new Error('Method not implemented.');
  }

  deleteAvailablity(availablityId: number) {
    return this._httpClient.delete<any>(environment.baseURL + environment.doctor + `/deleteAvailability/` + availablityId);
  }

  setAvailability(doctorId: number, availability: any) {
    return this._httpClient.post<any>(environment.baseURL + environment.doctor + `/setAvailability/` + doctorId, availability);
  }
  getAvailability(doctorId: number, availability: any) {
    return this._httpClient.post<any>(environment.baseURL + environment.doctor + `/getAvailability/` + doctorId, availability);
  }
  viewDoctorProfile(): Observable<any> {
    return this._httpClient.get<any>(environment.baseURL + environment.doctor + `/viewprofile`);
  }
  sendPrescription(appointmentId: number, prescription: any): Observable<any> {
    // return this.httpClient.post<any>(environment.baseURL + environment.doctor + `/sendPrescription/`+appointmentId);
    return this._httpClient.post<any>(environment.baseURL + environment.doctor + `/sendPrescription/` + appointmentId, prescription);
  }
  reply(commentId: any, comment: any): Observable<any> {
    return this._httpClient.post<any>(environment.baseURL + environment.doctor + `/reply/` + commentId, comment);
  }
  heartclick(commentId: any): Observable<any> {
    // console.log(doctorProfile.userId);
    return this._httpClient.get<any>(environment.baseURL + environment.doctor + `/like/` + commentId);
  }
  setDoctorProfile(doctorProfile: any): Observable<any> {
    console.log(doctorProfile.userId);
    return this._httpClient.post<any>(environment.baseURL + environment.doctor + `/savedoctorprofile`, doctorProfile);
  }
  setStatusUpdate(AppointmentId: any): Observable<any> {
    return this._httpClient.put<any>(environment.baseURL + environment.doctor + `/updateAppointmentStatus/` + AppointmentId, null);
  }
  getWarning(email: any): Observable<any> {
    return this._httpClient.get<any>(environment.baseURL + environment.doctor + `/concerns/` + email);
  }
}
