
import { RouterModule, Routes } from '@angular/router';
import { ViewAppointmentsComponent } from './components/view-appointments/view-appointments.component';
import { PatientService } from './services/patient.service';
import { SearchDoctorsComponent } from './components/search-doctors/search-doctors.component';
import { ViewProfileComponent } from './components/view-profile/view-profile.component';
import { SharedModule } from '../shared/shared.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CreateProfileComponent } from './components/create-profile/create-profile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BasicAuthHtppInterceptorService } from '../home/services/basic-auth-htpp-interceptor.service';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { NgxMatFileInputModule } from '@angular-material-components/file-input';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ViewDocprofileComponent } from './components/view-docprofile/view-docprofile.component';
import { ViewCommentsComponent } from './components/view-comments/view-comments.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { ViewPrescriptionComponent } from './components/view-prescription/view-prescription.component';
import { FillConcernsComponent } from './components/fill-concerns/fill-concerns.component';
import { BookAppointmentComponent } from './components/book-appointment/book-appointment.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatDialogModule } from '@angular/material/dialog';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
const routes: Routes = [
  {
    path: '',
    component: NavbarComponent,
    children: [
      { path: 'search', component: SearchDoctorsComponent },
      { path: 'appointments', component: ViewAppointmentsComponent },
      { path: 'profile', component: ViewProfileComponent },
      { path: '', redirectTo: 'profile', pathMatch: 'full' },
      { path: 'create-profile', component: CreateProfileComponent },
      { path: 'fill-concerns', component: FillConcernsComponent },
      { path: 'view-prescription', component: ViewPrescriptionComponent },
      { path: 'book-appointment', component: BookAppointmentComponent },
      { path: 'view-docprofile/:id', component: ViewDocprofileComponent },
      { path: 'view-comment/:id', component: ViewCommentsComponent },
      { path: 'feedback', component: FeedbackComponent }
    ]
  }
];

@NgModule({
  declarations: [
    ViewAppointmentsComponent,
    SearchDoctorsComponent,
    ViewProfileComponent,
    NavbarComponent,
    CreateProfileComponent,
    FillConcernsComponent,
    ViewPrescriptionComponent,
    BookAppointmentComponent,
    // PatientAppointmentListComponent
    ViewDocprofileComponent,
    ViewCommentsComponent,
    FeedbackComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild(routes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatRadioModule,
    NgxMatFileInputModule,
    MatIconModule,
    MatCardModule,
    FlexLayoutModule,
    NgbModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDialogModule
  ],
  exports: [RouterModule],
  providers: [PatientService, { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true }]
})
export class PatientModule { }
