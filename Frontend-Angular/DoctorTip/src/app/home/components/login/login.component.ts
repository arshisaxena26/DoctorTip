import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomvalidationService } from '../../services/customvalidation.service';
import { LoginService } from '../../services/login.service';
import { ToastrService } from 'ngx-toastr';

declare var $: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginform: FormGroup;
  registerform: FormGroup;
  submitted = false;
  msg: any;


  constructor(
    private _loginService: LoginService,
    private _router: Router,
    private fb: FormBuilder,
    private _customValidator: CustomvalidationService,
    private _toastrService: ToastrService
  ) { }

  ngOnInit(): void {
    this._toastrService.toastrConfig.positionClass = 'toast-top-center';
    this.msg = history.state.errormsg;

    this.loginform = this.fb.group({
      emailld: ['', [Validators.required, Validators.email]],
      password: [
        '',
        Validators.compose([
          Validators.required,
          this._customValidator.patternValidator(),
        ]),
      ],
    });

    this.registerform = this.fb.group(
      {
        email: [
          '',
          Validators.compose([
            Validators.required,
            Validators.email,
            this._customValidator.userEmailValidator.bind(this._customValidator),
          ]),
        ],
        username: ['', [Validators.required]],
        password: [
          '',
          Validators.compose([
            Validators.required,
            this._customValidator.patternValidator(),
          ]),
        ],
        confirmPassword: ['', [Validators.required]],
        role: ['', Validators.required],
      },
      {
        validator: this._customValidator.MatchPassword(
          'password',
          'confirmPassword'
        ),
      }
    );
  }

  ngAfterViewInit() {
    $('.login-info-box').fadeOut();
    $('.login-show').addClass('show-log-panel');

    $('.login-reg-panel input[type="radio"]').on('change', function () {
      if ($('#log-login-show').is(':checked')) {
        $('.register-info-box').fadeOut();
        $('.login-info-box').fadeIn();

        $('.white-panel').addClass('right-log');
        $('.register-show').addClass('show-log-panel');
        $('.login-show').removeClass('show-log-panel');
      } else if ($('#log-reg-show').is(':checked')) {
        $('.register-info-box').fadeIn();
        $('.login-info-box').fadeOut();

        $('.white-panel').removeClass('right-log');

        $('.login-show').addClass('show-log-panel');
        $('.register-show').removeClass('show-log-panel');
      }
    });
  }

  login() {
    this._loginService
      .authenticate(this.loginform.value.emailld, this.loginform.value.password)
      .subscribe((data) => {
        if (data.role == 'ROLE_PATIENT') {
          if (data.foundEntry === true) {
            //if patient has already profile filled
            this._router.navigate(['/patient/profile'], {
              state: { userId: data.userId, userName: data.userName },
            });
          } else {
            //if patient has not fill profile
            this._router.navigate(['/patient/create-profile'], {
              state: { userId: data.userId, userName: data.userName },
            });
          }
        } else if (data.role == 'ROLE_DOCTOR') {
          if (data.foundEntry === true) {
            //if doctor has already profile filled
            this._router.navigate(['/doctor/profile'], {
              state: { userId: data.userId, userName: data.userName },
            });
          } else {
            //if doctor  has not fill profile
            this._router.navigate(['/doctor/create-profile'], {
              state: { userId: data.userId, userName: data.userName },
            });
          }
        } else {
          this._router.navigate(['/admin/patients'], {
            state: { userId: data.userId, userName: data.userName },
          });
        }
      });
  }

  register() {

    const user: JSON = <JSON>(<unknown>{
      username: this.registerform.value.username,
      password: this.registerform.value.password,
      userEmail: this.registerform.value.email,
      userRole: this.registerform.value.role,
    });
    this._loginService.register(user).subscribe((data) => {
      this._toastrService.success("Successfully Register");
      location.reload();
    });
  }
}
