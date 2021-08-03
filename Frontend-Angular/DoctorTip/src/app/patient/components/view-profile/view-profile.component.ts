import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { isTemplateExpression } from 'typescript';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.scss']
})
export class ViewProfileComponent implements OnInit, OnDestroy {

  subscription: Subscription | undefined;
  patient : any;
  constructor(private _patientService: PatientService) {
    
   }

  ngOnInit(): void {
 
    this.subscription = this._patientService
      .showPatientProfile(sessionStorage.getItem('userId'))
      .subscribe((res: any) => {
        this.patient = res;
        this.patient.userEmail=sessionStorage.getItem('userEmail');
        this.patient.patientImage = 'data:image/jpeg;base64,' + this.patient.patientImage;
        
        
        console.log(this.patient);
      });
  
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
