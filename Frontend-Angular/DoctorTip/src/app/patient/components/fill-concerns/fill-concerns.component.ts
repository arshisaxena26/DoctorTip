import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CustomvalidationService } from 'src/app/home/services/customvalidation.service';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-fill-concerns',
  templateUrl: './fill-concerns.component.html',
  styleUrls: ['./fill-concerns.component.scss'],
})
export class FillConcernsComponent implements OnInit {
  subscription: Subscription | undefined;
  isFormSubmited: boolean = false;
  concernForm: FormGroup;
  @Input() appointmentId: any;

  constructor(
    private _patientService: PatientService,
    private _router: Router,
    private fb: FormBuilder,
    private _customValidator: CustomvalidationService
  ) {
    this.concernForm = this.fb.group({
      concernComment: [
        '',
        [Validators.required, _customValidator.wordlengthValidator()],
      ],
    });
  }
  ngOnInit(): void {

  }

  onSubmit() {

    const fillConcernForm: JSON = <JSON>(<unknown>{
      concernComment: this.concernForm.value.concernComment,
      appointmentId: this.appointmentId,
    });

    this.subscription = this._patientService
      .fillConcerns(fillConcernForm)
      .subscribe((res: any) => {
        location.reload();
      });
  }
}
