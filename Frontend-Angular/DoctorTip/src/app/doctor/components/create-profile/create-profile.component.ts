import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CustomvalidationService } from 'src/app/home/services/customvalidation.service';
import { DoctorService } from '../../services/doctor.service';
declare var $: any;

@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.scss'],
})
export class CreateProfileComponent implements OnInit {
  subscription: Subscription | undefined;
  doctorProfile: FormGroup;
  isFormSubmited: boolean = false;
  userId: any;
  constructor(
    private _doctorService: DoctorService,
    private _router: Router,
    private fb: FormBuilder,
    private _customValidator: CustomvalidationService
  ) {
    this.doctorProfile = this.fb.group({
      doctorClinicName: [
        '',
        [Validators.required, _customValidator.stringValidator()],
      ],
      doctorLocation: [
        '',
        [Validators.required, _customValidator.stringValidator()],
      ],
      doctorSpecialization: [
        '',
        [Validators.required, _customValidator.stringValidator()],
      ],
      doctorExperience: [
        '',
        [Validators.required, _customValidator.experienceValidator()],
      ],
      doctorConsultationFee: [
        '',
        [Validators.required, Validators.pattern(new RegExp('^[0-9]+$'))],
      ],
      doctorPhone: [
        '',
        [
          Validators.required,
          Validators.pattern(new RegExp('^([0-9]{1})([234789]{1})([0-9]{8})$')),
        ],
      ],
      doctorGender: ['', Validators.required],
      doctorImage: ['', Validators.required],
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
    
    if (!this.doctorProfile.valid) {
      this.isFormSubmited = true; // set value for gender validation
      return;
    }

    const doctorProfile = new FormData();
    doctorProfile.append('userId', this.userId);
    doctorProfile.append(
      'doctorClinicName',
      this.doctorProfile.value.doctorClinicName
    );
    doctorProfile.append(
      'doctorLocation',
      this.doctorProfile.value.doctorLocation
    );
    doctorProfile.append(
      'doctorSpecialization',
      this.doctorProfile.value.doctorSpecialization
    );
    doctorProfile.append(
      'doctorExperience',
      this.doctorProfile.value.doctorExperience
    );
    doctorProfile.append(
      'doctorConsultationFee',
      this.doctorProfile.value.doctorConsultationFee
    );
    doctorProfile.append('doctorPhone', this.doctorProfile.value.doctorPhone);
    doctorProfile.append('doctorGender', this.doctorProfile.value.doctorGender);
    doctorProfile.append('doctorImage', this.doctorProfile.value.doctorImage);

    this.subscription = this._doctorService
      .setDoctorProfile(doctorProfile)
      .subscribe((res: any) => {
        this._router.navigate(['/doctor/profile']);
      });
  }
}
