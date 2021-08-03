import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute } from '@angular/router';
import { DoctorService } from '../../services/doctor.service';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-availablity',
  templateUrl: './availablity.component.html',
  styleUrls: ['./availablity.component.scss']
})
export class AvailablityComponent implements OnInit {
  id: number = 0;
  doctors: any;
  subscription: Subscription | undefined;
  AvailabilityForm: FormGroup;
  currentDate = formatDate(new Date(), 'yyyy-MM-dd', 'en');
  constructor(private availabilityService: DoctorService, private _doctorService: DoctorService, private _activeRoute: ActivatedRoute, private fb: FormBuilder, private _router: Router) {
    _activeRoute.params.subscribe(p => {
      this.id = p.id;
      console.log(this.id);

    });
    this.AvailabilityForm = this.fb.group({
      availablityFrom: ['', Validators.required],
      availablityTo: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.fetchAvailablity();
  }

  fetchAvailablity() {
    this.subscription = this._doctorService
      .viewDoctorProfile()
      .subscribe((res: any) => {
        this.doctors = res;

      });
  }
  // displayTable()
  // {
  //     if (document.getElementById("displaytable").style.display === "none")
  //         document.getElementById("displaytable").style.display="block";
  //     else
  //         document.getElementById("displaytable").style.display="none";
  // }
  onSend() {
    console.log(this.AvailabilityForm.value)

    this.subscription = this.availabilityService
      .setAvailability(this.id, this.AvailabilityForm.value)
      .subscribe((res: any) => {
        location.reload();
      });

  }

  delete(availabilityId: any) {
    console.log(availabilityId)


    this.subscription = this.availabilityService
      .deleteAvailablity(availabilityId)
      .subscribe((res: any) => {
        console.log(res)
        this.fetchAvailablity();
      });

  }
  //  var a=0;
  // count(){
  //  a=a+1;

  // }
  // i=0;
}
