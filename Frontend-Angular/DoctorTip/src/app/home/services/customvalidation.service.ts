import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ValidatorFn, AbstractControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomvalidationService {

  constructor(private _httpClient: HttpClient) { }

  patternValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$');
      const valid = regex.test(control.value);
      return valid ? null : { invalidPassword: true };
    };
  }

  MatchPassword(password: string, confirmPassword: string) {
    return (formGroup: FormGroup) => {
      const passwordControl = formGroup.controls[password];
      const confirmPasswordControl = formGroup.controls[confirmPassword];

      if (!passwordControl || !confirmPasswordControl) {
        return null;
      }

      if (confirmPasswordControl.errors && !confirmPasswordControl.errors.passwordMismatch) {
        return null;
      }

      if (passwordControl.value !== confirmPasswordControl.value) {
        confirmPasswordControl.setErrors({ passwordMismatch: true });
      } else {
        confirmPasswordControl.setErrors(null);
      }
    }
  }



  async userEmailValidator(userControl: AbstractControl) {
    let emailFlag: boolean;
    if (!userControl) {
      return null;
    }


    return new Promise(resolve => {
      setTimeout(async () => {
        emailFlag = await this.validateUserEmail(userControl.value)
        if (emailFlag) {
          userControl.setErrors({ userEmailNotAvailable: true });
        }
        else if (userControl.errors.required) {
          // required error
          userControl.setErrors({ required: true });
        }
        else if (userControl.errors.email) {
          //email error
          userControl.setErrors({ email: true });
        }
        else {
          // else part
          userControl.setErrors(null);
        }
      }, 1000);
    });
  }

  validateUserEmail(email: string): Promise<any> {
    const validEmail = this._httpClient.post<any>(environment.baseURL + 'checkEmail', { email }).toPromise();
    return validEmail;
  }


  /* For Profile form*/
  ageValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } => {
      if (control.value > 1 && control.value < 100) {
        return null;
      } else {
        return { age: true };
      }
    };
  }
  experienceValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } => {
      if (control.value > 0 && control.value < 51) {
        return null;
      } else {
        return { experience: true };
      }
    };
  }
  stringValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('^[a-zA-Z][a-zA-Z ]*$');
      const valid = regex.test(control.value);
      return valid ? null : { stringPattern: true };
    };
  }
  wordlengthValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } => {
      if (control.value.length < 51) {
        return null;
      } else {
        return { wordCount: true };
      }
    };
  }
}