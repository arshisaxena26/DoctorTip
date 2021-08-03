import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { IDoctorDTO } from '../../models/idoctor-dto';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-view-doctors',
  templateUrl: './view-doctors.component.html',
  styleUrls: ['./view-doctors.component.scss']
})
export class ViewDoctorsComponent implements OnInit, OnDestroy {
  subscription: Subscription | undefined;
  doctors: IDoctorDTO[] = [];

  constructor(private _adminService: AdminService, private _router: Router) { }

  ngOnInit(): void {
    this.subscription = this._adminService
      .getDoctorList()
      .subscribe((res: any) => {
        this.doctors = res;
        this.doctors.forEach(doctor => doctor.image = 'data:image/jpeg;base64,' + doctor.image);
      });
  }

  navigate(id: number) {
    this._router.navigate(["/admin/concerns/" + id]);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
