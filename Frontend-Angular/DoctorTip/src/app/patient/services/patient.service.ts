import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PatientService {
  

  constructor(private _httpClient: HttpClient) { }
  setPatientProfile(patientProfile: any): Observable<any> {
    return this._httpClient.post<any>(environment.baseURL + environment.patient + `/savepatientprofile`, patientProfile);
  }

  getDoctorSpecialization(): Observable<any> {
    return this._httpClient.get<any>(environment.baseURL + environment.patient + `/getspecializations`);
  }

  searchDoctor(searchDoctor: any): Observable<any> {
    return this._httpClient.post<any>(environment.baseURL + environment.patient + `/listofdoctor`, searchDoctor);
  }

  showPatientProfile(id : any) : Observable<any>{
    return this._httpClient.get<any>(environment.baseURL + environment.patient + `/patprofile/`+ id);
  }

  showDoctorProfile(id : any) : Observable<any>{
    return this._httpClient.get<any>(environment.baseURL + environment.patient + `/docprofile/`+ id);
  }

  showComment(id : any): Observable<any>{
    return this._httpClient.get<any>(environment.baseURL + environment.patient + `/comment/`+ id);
  }

  saveComment(comment: any): Observable<any> {
    return this._httpClient.post<any>(environment.baseURL + environment.patient + `/savecomment`, comment);
  }
  showLikeDislike(docId : any,userId:any) : Observable<any>{
    return this._httpClient.get<any>(environment.baseURL + environment.patient + `/getlikedislike/`+ docId+`/`+userId);
  }
  showLikeCount(docId : any) : Observable<any>{
    return this._httpClient.get<any>(environment.baseURL + environment.patient + `/getlikecount/`+ docId);
  }
  showDisikeCount(docId : any) : Observable<any>{
    return this._httpClient.get<any>(environment.baseURL + environment.patient + `/getdislikecount/`+ docId);
  }
  

  
  fillConcerns(concernForm: any): Observable<any> {
    console.log(concernForm)
    return this._httpClient.post<any>(environment.baseURL + environment.patient + '/concerns', concernForm)
  }

  getPrescription(appointmentId: any): Observable<any> {

    return this._httpClient.get<any>(environment.baseURL + environment.patient + '/prescriptions?appointmentId=' + appointmentId)
  }

  getAvailableTimeSlot(date: any, id: any): Observable<any> {
    return this._httpClient.get<any>(environment.baseURL + environment.patient + '/makeappointments?date=' + date + '&id=' + id)
  }

  bookAppointment(appointment: any): Observable<any> {
    return this._httpClient.post<any>(environment.baseURL + environment.patient + '/submitappointment', appointment)
  }

  getAppoinmentList(patientId: any): Observable<any> {
    return this._httpClient.get<any>(environment.baseURL + environment.patient +'/patientappointmentlist?patientId=' +patientId)
  }
  setLikeDislike(likeDislikeObj: any): Observable<any> {
    return this._httpClient.post<any>(environment.baseURL + environment.patient + `/setlikedislike`, likeDislikeObj);
  }
  addFeedback(feedback:any, id:any):Observable<any> {
    return this._httpClient.post<any>(environment.baseURL + environment.patient + `/savefeed/${id}`, feedback);
  }
}
