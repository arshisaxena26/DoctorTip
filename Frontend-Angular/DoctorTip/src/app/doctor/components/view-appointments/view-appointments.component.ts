import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DoctorService } from '../../services/doctor.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-view-appointments',
  templateUrl: './view-appointments.component.html',
  styleUrls: ['./view-appointments.component.scss']
})
export class ViewAppointmentsComponent implements OnInit, OnDestroy {
  appointments: any;
  subscription: Subscription | undefined;

  constructor(private _router: Router, private _doctorService: DoctorService) { }

  ngOnInit(): void {
    this.subscription = this._doctorService
      .viewDoctorAppointmentent()
      .subscribe((res: any) => {
        this.appointments = res;

        console.log(this.appointments);
      });
    //  console.log(this.appointments);
  }
  // getAppointments() {
  //   this.subscription = this._doctorService
  //     .viewDoctorAppointmentent()
  //     .subscribe((res: any) => {
  //       this.appointments = res;

  //       console.log(this.appointments);
  //     });
  // }
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
  navigateTo(AppointmentId: any) {
    // {appointmentId :AppointmentId}
    this._router.navigate(["/doctor/fill-prescription/" + AppointmentId]);

  }

  StatusUpdate(appointmentId: any) {
    this.subscription = this._doctorService
      .setStatusUpdate(appointmentId)
      .subscribe((res: any) => {
        location.reload();
      });
  }

}
function doctorId(doctorId: any) {
  throw new Error('Function not implemented.');
}

