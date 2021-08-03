import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentStatsChartComponent } from './appointment-stats-chart.component';

describe('AppointmentStatsChartComponent', () => {
  let component: AppointmentStatsChartComponent;
  let fixture: ComponentFixture<AppointmentStatsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentStatsChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentStatsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
