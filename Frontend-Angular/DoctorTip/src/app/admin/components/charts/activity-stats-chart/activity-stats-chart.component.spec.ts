import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityStatsChartComponent } from './activity-stats-chart.component';

describe('ActivityStatsChartComponent', () => {
  let component: ActivityStatsChartComponent;
  let fixture: ComponentFixture<ActivityStatsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivityStatsChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityStatsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
