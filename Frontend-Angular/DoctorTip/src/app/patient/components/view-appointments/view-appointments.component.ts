import { Component, OnDestroy, Output, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MatTableDataSource } from '@angular/material/table';
import { PatientService } from '../../services/patient.service';
import { MatPaginator } from '@angular/material/paginator';
import { AppointmentDTO } from '../../models/appointment-dto';
import { PrescriptionDTO } from '../../models/prescription-dto';
import { MatDialog } from '@angular/material/dialog';
@Component({
  selector: 'app-view-appointments',
  templateUrl: './view-appointments.component.html',
  styleUrls: ['./view-appointments.component.scss']
})
export class ViewAppointmentsComponent implements OnDestroy {
  @ViewChild('prescription') prescriptionDialog: TemplateRef<any>;
  @ViewChild('concern') concernDialog: TemplateRef<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource: MatTableDataSource<AppointmentDTO>;

  dataLength: number;
  subscription: Subscription | undefined;
  prescriptionData: PrescriptionDTO;
  patientId: any;
  appId: any;
  feedid: any;
  displayedColumns = [
    "Date",
    "Status",
    "Doctor Name",
    "Clinic Name",
    "Time",
    "Operations"
  ];
  constructor(private _patientService: PatientService, private _router: Router, private dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.patientId = sessionStorage.getItem("userId")
    this.subscription = this._patientService.getAppoinmentList(this.patientId).subscribe((res: any) => {
      this.dataLength = res.length;
      this.dataSource = new MatTableDataSource(res);
      console.log(res);
      setTimeout(() => {
        this.dataSource.paginator = this.paginator;
      });
    });
  }
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  showPrescription(id) {
    this.dialog.open(this.prescriptionDialog);
    this.appId = id;
  }

  fillConcern(id) {
    this.appId = id;
    this.dialog.open(this.concernDialog);
  }

  fillfeedback(id: number) {
    sessionStorage.setItem('feedid', id.toString());
    this._router.navigate(["/patient/feedback/"]);
  }
}
