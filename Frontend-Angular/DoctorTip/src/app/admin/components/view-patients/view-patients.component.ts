import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { IPatientDTO } from '../../models/ipatient-dto';
import { AdminService } from '../../services/admin.service';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';

@Component({
  selector: 'app-view-patients',
  templateUrl: './view-patients.component.html',
  styleUrls: ['./view-patients.component.scss']
})
export class ViewPatientsComponent implements OnInit, OnDestroy {
  subscription: Subscription | undefined;
  patients: IPatientDTO[] = [];

  constructor(private _adminService: AdminService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.subscription = this._adminService
      .getPatientList()
      .subscribe((res: any) => {
        this.patients = res;
        this.patients.forEach(patient => patient.image = 'data:image/jpeg;base64,' + patient.image);
      });
  }

  deactivate(id: number) {
    const confirmDialog = this.dialog.open(DialogBoxComponent, {
      data: {
        title: 'Confirm Deactivate User',
        message: 'Are you sure, you want to deactivate user?'
      }
    });
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {
        this.subscription = this._adminService.
          deactivatePatient(id).
          subscribe((res: any) => {
            location.reload();
          });
      }
    });
  }

  activate(id: number) {
    const confirmDialog = this.dialog.open(DialogBoxComponent, {
      data: {
        title: 'Confirm Activate User',
        message: 'Are you sure, you want to activate user?'
      }
    });
    confirmDialog.afterClosed().subscribe(result => {
      if (result === true) {
        this.subscription = this._adminService.
          activatePatient(id).
          subscribe((res: any) => {
            location.reload();
          });
      }
    });

  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
