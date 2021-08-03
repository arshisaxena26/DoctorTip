import { Component, OnDestroy, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { IDoctorStats } from '../../models/idoctor-stats';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-popularity-table',
  templateUrl: './popularity-table.component.html',
  styleUrls: ['./popularity-table.component.scss']
})
export class PopularityTableComponent implements OnDestroy {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  dataSource: MatTableDataSource<IDoctorStats>;
  dataLength: number;
  subscription: Subscription | undefined;

  displayedColumns = [
    "Name",
    "Likes",
    "Appointments",
    "Concerns",
    "Warnings"
  ];

  constructor(private _adminService: AdminService) {
    this.subscription = this._adminService
      .getDoctorStats()
      .subscribe((res: any) => {
        this.dataLength = res.length;
        this.dataSource = new MatTableDataSource(res);
        setTimeout(() => {
          this.dataSource.paginator = this.paginator;
        });
      });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
