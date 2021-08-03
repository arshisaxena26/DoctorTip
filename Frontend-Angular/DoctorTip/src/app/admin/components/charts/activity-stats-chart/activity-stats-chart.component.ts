import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Chart } from 'chart.js';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/admin/services/admin.service';

@Component({
  selector: 'app-activity-stats-chart',
  templateUrl: './activity-stats-chart.component.html',
  styleUrls: ['./activity-stats-chart.component.scss']
})
export class ActivityStatsChartComponent implements OnInit, OnDestroy, AfterViewInit {

  subscription: Subscription | undefined;
  chart: Chart;
  chartData: any[] = [];
  chartLabels: any[] = [];
  @ViewChild('canvas') canvas: ElementRef;

  constructor(private _adminService: AdminService) { }

  ngOnInit() {
    this.subscription = this._adminService
      .getActivityStats()
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
        type: 'doughnut',
        data: {
          labels: this.chartLabels,
          datasets: [
            {
              label: 'Activity',
              data: this.chartData,
              backgroundColor: ["#0074D9", "#FFDC00", "#2ECC40", "#FF4136"],
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

