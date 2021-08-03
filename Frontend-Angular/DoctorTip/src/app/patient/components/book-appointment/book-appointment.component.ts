import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TimeSlotDTO } from '../../models/time-slot-dto';
import { PatientService } from '../../services/patient.service';
declare var $: any;

@Component({
  selector: 'app-book-appointment',
  templateUrl: './book-appointment.component.html',
  styleUrls: ['./book-appointment.component.scss'],
})
export class BookAppointmentComponent implements OnInit {
  subscription: Subscription | undefined;
  timeSlotList: any;
  avaiableSlot: boolean;
  date: any;
  doctor: any;
  doctorId: any;
  patientUserId: any;
  selectedtimeslot: any;

  constructor(
    private _patientService: PatientService,
    private _router: Router
  ) { }

  ngOnInit(): void {
    this.doctor = JSON.parse(sessionStorage.getItem('doctor'));

    this.date = sessionStorage.getItem('appointmentDate');

    this.patientUserId = sessionStorage.getItem('userId');
    //getting avaiable slots
    this.subscription = this._patientService
      .getAvailableTimeSlot(this.date, this.doctor.id)
      .subscribe((res: any) => {
        this.timeSlotList = res;
        //set flag if slots are not avaiable
        this.avaiableSlot =
          Object.keys(this.timeSlotList).length > 0 ? true : false;
      });
  }

  radioChange(event: any) {
    this.selectedtimeslot = event.srcElement.defaultValue;
  }

  submitappointment() {
    const appointmentObj: JSON = <JSON>(<unknown>{
      patientUserId: this.patientUserId,
      doctorId: this.doctor.id,
      appointmentDate: this.date,
      timeSlot: this.selectedtimeslot,
    });

    this.subscription = this._patientService
      .bookAppointment(appointmentObj)
      .subscribe((res: any) => {
        this._router.navigate(["/patient/appointments"]);
      });
  }
}
