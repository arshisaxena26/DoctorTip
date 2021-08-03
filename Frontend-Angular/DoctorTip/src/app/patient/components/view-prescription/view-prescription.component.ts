import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { observable, Subscription } from 'rxjs';
import { PrescriptionDTO } from '../../models/prescription-dto';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-view-prescription',
  templateUrl: './view-prescription.component.html',
  styleUrls: ['./view-prescription.component.scss']
})
export class ViewPrescriptionComponent implements OnInit {
  subscription: Subscription | undefined;
  prescriptionData:PrescriptionDTO;
  @Input()appointmentId:any;
  constructor(private _patientService: PatientService, private _router: Router) { }

  ngOnInit(): void {
  
    this.subscription=this._patientService.getPrescription(this.appointmentId).subscribe((res: any)=>{

       this.prescriptionData=res;
    });
   
  }

  

}
