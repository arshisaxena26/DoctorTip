import { HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Observable, of, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHtppInterceptorService implements HttpInterceptor {

  constructor(private _loginService: LoginService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {


    if (sessionStorage.getItem('userEmail') && sessionStorage.getItem('token')) {

      req = req.clone({
        setHeaders: {
          Authorization: this._loginService.getToken()
        }
      })
    }

    return next.handle(req).pipe(tap(() => { },
      (err: any) => {
        if (err instanceof HttpErrorResponse) {

          if (err.status == 401) {
            this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
              this.router.navigate(['login'], {
                state: { errormsg: "Invalid Login Credentials" }
              })
            });
          }

          if (err.status == 423) {
            this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
              this.router.navigate(['login'], {
                state: { errormsg: "Account Blocked. Please Contact Admin." }
              })
            });
          }

          if (err.status == 403) {
            this.router.navigate(['**'], {
              state: { errormsg: "You are forbidden to allow this resource:403 error" }
            })
          }

          this.router.navigate(['/error']);
        }
      }));
  }

}
