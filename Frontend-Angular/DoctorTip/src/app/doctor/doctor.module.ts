import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ViewAppointmentsComponent } from './components/view-appointments/view-appointments.component';
import { DoctorService } from './services/doctor.service';
import { WarningsComponent } from './components/warnings/warnings.component';
import { ViewprofileComponent } from './components/viewprofile/viewprofile.component';
import { SharedModule } from '../shared/shared.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CreateProfileComponent } from './components/create-profile/create-profile.component';


import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BasicAuthHtppInterceptorService } from '../home/services/basic-auth-htpp-interceptor.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { NgxMatFileInputModule } from '@angular-material-components/file-input';
import { MatIconModule } from '@angular/material/icon';

import { AvailablityComponent } from './components/availablity/availablity.component';
import { NgModule } from '@angular/core';
import { FillPrescriptionComponent } from './components/fill-prescription/fill-prescription.component';

const routes: Routes = [
  {
    path: '',
    component: NavbarComponent,
    children: [
      { path: 'appointments', component: ViewAppointmentsComponent },
      { path: 'profile', component: ViewprofileComponent },
      { path: '', redirectTo: 'profile', pathMatch: 'full' },
      { path: 'create-profile', component: CreateProfileComponent },
      { path: 'warnings', component: WarningsComponent },
      { path: 'availablity/:id', component: AvailablityComponent },
      { path: 'fill-prescription/:id', component: FillPrescriptionComponent }

    ]
  }
];

@NgModule({
  declarations: [
    ViewAppointmentsComponent,
    WarningsComponent,
    ViewprofileComponent,
    NavbarComponent,
    CreateProfileComponent,
    FillPrescriptionComponent,
    AvailablityComponent

  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    NgxMatFileInputModule,
    MatIconModule
  ],
  exports: [RouterModule],
  providers: [DoctorService, { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true }]
})
export class DoctorModule { }
