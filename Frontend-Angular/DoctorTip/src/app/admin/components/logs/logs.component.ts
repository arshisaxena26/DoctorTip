import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { ILogsDTO } from '../../models/ilogs-dto';
import { AdminService } from '../../services/admin.service';


@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.scss']
})
export class LogsComponent implements OnInit, OnDestroy {
  subscription: Subscription | undefined;
  userMap = new Map<number, string>();
  searchForm: FormGroup;
  count: number = 0;
  showTable: boolean = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource: MatTableDataSource<ILogsDTO>;
  dataLength: number;

  displayedColumns = [
    "S.No",
    "Activity",
    "Date",
  ];

  constructor(private _adminService: AdminService, private fb: FormBuilder) {
    this.searchForm = this.fb.group({
      name: ['']
    })
  }

  ngOnInit(): void {
    this.subscription = this._adminService
      .getUsers()
      .subscribe((res: any) => {
        this.userMap = res;
      });
  }

  getLogs() {
    this.subscription = this._adminService
      .getLogs(this.searchForm.value.name.key)
      .subscribe((res: any) => {
        this.dataLength = res.length;
        this.dataSource = new MatTableDataSource(res);
        setTimeout(() => {
          this.dataSource.paginator = this.paginator;
        });
        this.showTable = true;
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
