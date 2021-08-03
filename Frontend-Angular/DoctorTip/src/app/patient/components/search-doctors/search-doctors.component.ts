import { formatDate } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CustomvalidationService } from 'src/app/home/services/customvalidation.service';
import { DoctorDTO } from '../../models/doctor-dto';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-search-doctors',
  templateUrl: './search-doctors.component.html',
  styleUrls: ['./search-doctors.component.scss']
})
export class SearchDoctorsComponent implements OnInit, OnDestroy {

  subscription: Subscription | undefined;
  specializations: any;
  searchForm: FormGroup;
  searchFlag:boolean;
  listOfDoctors: DoctorDTO[];
  doctorRating:number=4;
  currentDate= formatDate(new Date(), 'yyyy-MM-dd', 'en');
  selectedAppDate:any;
  constructor(private _patientService: PatientService, private _router: Router, private fb: FormBuilder,private _customValidator: CustomvalidationService) {
    
    this.searchForm = this.fb.group({
      doctorSpecialization: ['', ],
      appointmentDate: [formatDate(new Date(), 'yyyy-MM-dd', 'en'), [Validators.required]],
      doctorName: ['',  _customValidator.stringValidator()]
    })
  }

  ngOnInit(): void {
    this.subscription = this._patientService.getDoctorSpecialization().subscribe((res: any) => {
      this.specializations = res;
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  onSubmit() {
    if (!this.searchForm.valid) {
      return;
    }
    this.selectedAppDate =formatDate(this.searchForm.value.appointmentDate, 'yyyy-MM-dd', 'en');
    const searchDocotor = new FormData();
    searchDocotor.append("doctorSpecialization",this.searchForm.value.doctorSpecialization);
    searchDocotor.append("appointmentDate",this.selectedAppDate);
    searchDocotor.append("doctorName",this.searchForm.value.doctorName);
    this.subscription = this._patientService.searchDoctor(searchDocotor).subscribe((res: any) => {  
      this.listOfDoctors = res;
      this.listOfDoctors.forEach(item => 
        item.image='data:image/jpeg;base64,' + item.image
      );
      this.searchFlag=true;
    });
  }
  
  showProfile(selectedDoctor:any){
      sessionStorage.setItem("appointmentDate",this.selectedAppDate);
      sessionStorage.setItem("doctor",JSON.stringify(selectedDoctor));
      this._router.navigate([`/patient/view-docprofile/${selectedDoctor.id}`]);
  }
}
