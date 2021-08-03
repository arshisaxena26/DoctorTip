import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DoctorService } from '../../services/doctor.service';

@Component({
  selector: 'app-fill-prescription',
  templateUrl: './fill-prescription.component.html',
  styleUrls: ['./fill-prescription.component.scss']
})
export class FillPrescriptionComponent implements OnInit {
  PrescriptionForm: FormGroup;
  id: number = 0;
  subscription: Subscription | undefined;

  constructor(private prescriptionService: DoctorService, private _activeRoute: ActivatedRoute, private fb: FormBuilder, private _router: Router) {

    _activeRoute.params.subscribe(p => {
      this.id = p.id;
      console.log(this.id);

    });

    this.PrescriptionForm = this.fb.group({
      prescriptionContent: ['', Validators.required],
      doS: ['', Validators.required],
      dontS: ['', Validators.required],
      followUp: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    // console.log("Doctor "+appointmentId);
  }
  sendPrescription() {


  }

  //  const PrescriptionForm = new FormData();
  onSend() {
    console.log(this.PrescriptionForm.value)

    this.subscription = this.prescriptionService
      .sendPrescription(this.id, this.PrescriptionForm.value)
      .subscribe((res: any) => {
        this._router.navigate(['/doctor/appointments']);
      });

  }
}
