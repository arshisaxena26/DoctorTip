import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DoctorService } from '../../services/doctor.service';

@Component({
  selector: 'app-warnings',
  templateUrl: './warnings.component.html',
  styleUrls: ['./warnings.component.scss']
})
export class WarningsComponent implements OnInit, OnDestroy {
  concerns:any;
  subscription: Subscription | undefined;

  constructor(private _doctorService: DoctorService) { }

  ngOnInit(): void {
   if (sessionStorage.getItem('userEmail')){
    this.subscription = this._doctorService
    .getWarning(sessionStorage.getItem('userEmail'))
    .subscribe((res: any) => {
      this.concerns = res;
      console.log(this.concerns);
      // console.log(this.doctors.appointmentList.concern.concernComment)
    });
  }
}

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
