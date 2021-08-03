import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Chart } from 'chart.js';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/admin/services/admin.service';

@Component({
  selector: 'app-usage-stats-chart',
  templateUrl: './usage-stats-chart.component.html',
  styleUrls: ['./usage-stats-chart.component.scss']
})
export class UsageStatsChartComponent implements OnInit, OnDestroy, AfterViewInit {

  subscription: Subscription | undefined;
  chart: Chart;
  chartData: any[] = [];
  chartLabels: any[] = [];
  @ViewChild('canvas') canvas: ElementRef;

  constructor(private _adminService: AdminService) { }

  ngOnInit() {
    this.subscription = this._adminService
      .getUserStats()
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
        type: 'pie',
        data: {
          labels: this.chartLabels,
          datasets: [
            {
              label: 'Appointments',
              data: this.chartData,
              backgroundColor: ["#FF4136", "#0074D9", "#FFDC00", "#FF851B", "#2ECC40"],
              borderWidth: 1
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
        },
      });
    }, 1000);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
