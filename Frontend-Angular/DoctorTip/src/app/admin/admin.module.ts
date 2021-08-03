import { RouterModule, Routes } from '@angular/router';
import { ViewPatientsComponent } from './components/view-patients/view-patients.component';
import { ViewDoctorsComponent } from './components/view-doctors/view-doctors.component';
import { AdminService } from './services/admin.service';
import { SharedModule } from '../shared/shared.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LogsComponent } from './components/logs/logs.component';
import { ConcernsComponent } from './components/concerns/concerns.component';
import { BasicAuthHtppInterceptorService } from '../home/services/basic-auth-htpp-interceptor.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { LayoutModule } from '@angular/cdk/layout';
import { ReportsComponent } from './components/reports/reports.component';
import { CardComponent } from './components/card/card.component';
import { PopularityTableComponent } from './components/popularity-table/popularity-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MiniCardComponent } from './components/mini-card/mini-card.component';
import { MatInputModule } from '@angular/material/input';
import { AppointmentStatsChartComponent } from './components/charts/appointment-stats-chart/appointment-stats-chart.component';
import { ActivityStatsChartComponent } from './components/charts/activity-stats-chart/activity-stats-chart.component';
import { RatingStatsChartComponent } from './components/charts/rating-stats-chart/rating-stats-chart.component';
import { UsageStatsChartComponent } from './components/charts/usage-stats-chart/usage-stats-chart.component';
import { DialogBoxComponent } from './components/dialog-box/dialog-box.component';
import { MatDialogModule } from '@angular/material/dialog';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

const routes: Routes = [
  {
    path: '',
    component: NavbarComponent,
    children: [
      { path: 'doctors', component: ViewDoctorsComponent },
      { path: 'patients', component: ViewPatientsComponent },
      { path: '', redirectTo: 'patients', pathMatch: 'full' },
      { path: 'reports', component: ReportsComponent },
      { path: 'logs', component: LogsComponent },
      { path: 'concerns/:id', component: ConcernsComponent }
    ]
  }
];

@NgModule({
  declarations: [
    ViewPatientsComponent,
    NavbarComponent,
    LogsComponent,
    ViewDoctorsComponent,
    ConcernsComponent,
    ReportsComponent,
    CardComponent,
    PopularityTableComponent,
    MiniCardComponent,
    AppointmentStatsChartComponent,
    ActivityStatsChartComponent,
    RatingStatsChartComponent,
    UsageStatsChartComponent,
    DialogBoxComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild(routes),
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    ReactiveFormsModule,
    ChartsModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    LayoutModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatInputModule,
    MatDialogModule
  ],
  exports: [RouterModule],
  providers: [AdminService, , { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true }]
})
export class AdminModule { }
