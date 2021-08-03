import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsageStatsChartComponent } from './usage-stats-chart.component';

describe('UsageStatsChartComponent', () => {
  let component: UsageStatsChartComponent;
  let fixture: ComponentFixture<UsageStatsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsageStatsChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsageStatsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
