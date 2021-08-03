import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private _httpClient: HttpClient) { }

  getToken() {
    return sessionStorage.getItem('token');
  }

  authenticate(userEmail: string, userPassword: string): Observable<any> {
    return this._httpClient
      .post<any>(environment.baseURL + `authenticate`, {
        userEmail,
        userPassword,
      })
      .pipe(
        map((userData) => {
          sessionStorage.setItem('userId', userData.userId);
          sessionStorage.setItem('userEmail', userEmail);
          let tokenStr = 'Bearer ' + userData.jwttoken;
          sessionStorage.setItem('token', tokenStr);
          return userData;
        })
      );
  }

  register(user: any): Observable<any> {
    return this._httpClient.post<any>(environment.baseURL + `register`, user);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('userId');
    return !(user === null);
  }

  logOut(): Observable<any> {
    const email = sessionStorage.getItem('userEmail');
    sessionStorage.removeItem('userEmail');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('token');
    return this._httpClient.post<any>(environment.baseURL + 'logoutt', { email });
  }
}
