import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CustomvalidationService } from 'src/app/home/services/customvalidation.service';
import { PatientService } from '../../services/patient.service';
declare var $: any;

@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.scss'],
})
export class CreateProfileComponent implements OnInit {
  subscription: Subscription | undefined;
  isFormSubmited: boolean = false;
  patientProfile: FormGroup;
  userId: any;
  constructor(
    private _patientService: PatientService,
    private _router: Router,
    private fb: FormBuilder,
    private _customValidator: CustomvalidationService
  ) {
    this.patientProfile = this.fb.group({
      patientAge: ['', [Validators.required, _customValidator.ageValidator()]],
      patientBloodGroup: ['', Validators.required],
      patientPhone: [
        '',
        [
          Validators.required,
          Validators.pattern(new RegExp('^([0-9]{1})([234789]{1})([0-9]{8})$')),
        ],
      ],
      patientLocation: [
        '',
        [Validators.required, _customValidator.stringValidator()],
      ],
      patientGender: ['', Validators.required],
      patientImage: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    // get the userid from the session storage
    this.userId = sessionStorage.getItem('userId');
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  onSubmit() {
    
    if (!this.patientProfile.valid) {
      this.isFormSubmited = true; // set value for gender validation
      return;
    }
    const patientProfile = new FormData();
    patientProfile.append('userId', this.userId);
    patientProfile.append(
      'patientBloodGroup',
      this.patientProfile.value.patientBloodGroup
    );
    patientProfile.append('patientAge', this.patientProfile.value.patientAge);
    patientProfile.append(
      'patientLocation',
      this.patientProfile.value.patientLocation
    );
    patientProfile.append(
      'patientGender',
      this.patientProfile.value.patientGender
    );
    patientProfile.append(
      'patientPhone',
      this.patientProfile.value.patientPhone
    );
    patientProfile.append(
      'patientImage',
      this.patientProfile.value.patientImage
    );

    this.subscription = this._patientService.setPatientProfile(patientProfile).subscribe((res: any) => {
      this._router.navigate(['/patient/profile']);
    });
  }
}
