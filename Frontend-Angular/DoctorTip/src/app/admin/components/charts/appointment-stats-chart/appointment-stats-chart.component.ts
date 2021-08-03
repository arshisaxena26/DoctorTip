import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Chart } from 'chart.js';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/admin/services/admin.service';

@Component({
  selector: 'app-appointment-stats-chart',
  templateUrl: './appointment-stats-chart.component.html',
  styleUrls: ['./appointment-stats-chart.component.scss']
})
export class AppointmentStatsChartComponent implements OnInit, OnDestroy, AfterViewInit {
  subscription: Subscription | undefined;
  chart: Chart;
  chartData: any[] = [];
  chartLabels: any[] = [];
  @ViewChild('canvas') canvas: ElementRef;

  constructor(private _adminService: AdminService) { }

  ngOnInit() {
    this.subscription = this._adminService
      .getAppointmentStats()
      .subscribe({
        next: items => {
          items.forEach(li => {
            this.chartData.push(li.count);
            this.chartLabels.push(li.label);
          });
        },
      });
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.chart = new Chart(this.canvas.nativeElement.getContext('2d'), {
        type: 'line',
        data: {
          labels: this.chartLabels,
          datasets: [
            {
              label: 'Appointments',
              data: this.chartData,
              borderColor: '#3cba9f',
              backgroundColor: '#3cba9f',
              fill: false
            }
          ]
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: 'top'
            }
          }
        }
      });
    }, 1000);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
