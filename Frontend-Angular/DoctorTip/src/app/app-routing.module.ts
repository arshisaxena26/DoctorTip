import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './home/components/login/login.component';
import { PageNotFoundComponent } from './home/components/page-not-found/page-not-found.component';
import { AuthGuard } from './home/guards/auth-guard.guard';

const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module').then((m) => m.AdminModule)
  },
  {
    path: 'doctor',
    loadChildren: () =>
      import('./doctor/doctor.module').then(
        (m) => m.DoctorModule
      )
  },
  {
    path: 'patient',
    loadChildren: () =>
      import('./patient/patient.module').then(
        (m) => m.PatientModule
      )
  },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
