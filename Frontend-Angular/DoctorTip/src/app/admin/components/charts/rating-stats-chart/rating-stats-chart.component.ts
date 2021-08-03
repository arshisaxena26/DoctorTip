import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Chart } from 'chart.js';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/admin/services/admin.service';

@Component({
  selector: 'app-rating-stats-chart',
  templateUrl: './rating-stats-chart.component.html',
  styleUrls: ['./rating-stats-chart.component.scss']
})
export class RatingStatsChartComponent implements OnInit, OnDestroy, AfterViewInit {

  subscription: Subscription | undefined;
  chart: Chart;
  chartData: any[] = [];
  chartLabels: any[] = [];
  @ViewChild('canvas') canvas: ElementRef;

  constructor(private _adminService: AdminService) { }

  ngOnInit() {
    this.subscription = this._adminService
      .getRatingStats()
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
        type: 'bar',
        data: {
          labels: this.chartLabels,
          datasets: [
            {
              label: 'Appointments',
              data: this.chartData,
              backgroundColor: '#3cba9f'
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

