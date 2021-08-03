import { AfterViewInit, ChangeDetectorRef, Component, Input, OnDestroy, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { AdminService } from '../../services/admin.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss']
})
export class ReportsComponent implements OnInit, OnDestroy, AfterViewInit {
  subscription: Subscription | undefined;
  miniCardData: Map<string, number>;
  // chartData: any[] = [];
  // chartLabels: any[] = [];

  cardLayout = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return {
          columns: 1,
          miniCard: { cols: 1, rows: 1 },
          chart: { cols: 1, rows: 2 },
          table: { cols: 1, rows: 4 },
        };
      }

      return {
        columns: 4,
        miniCard: { cols: 1, rows: 1 },
        chart: { cols: 2, rows: 2 },
        table: { cols: 4, rows: 3 },
      };
    })
  );

  constructor(private breakpointObserver: BreakpointObserver, private _adminService: AdminService, private cdr: ChangeDetectorRef) {
    console.log("Parent Constructor");
  }

  ngOnInit(): void {
    this.subscription = this._adminService
      .getStats()
      .subscribe((res: any) => {
        this.miniCardData = res;
      });
    console.log("Parent Init");
  }

  ngAfterViewInit() {
    this.cdr.detectChanges();
    console.log("Parent View");
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
