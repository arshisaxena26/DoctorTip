import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatingStatsChartComponent } from './rating-stats-chart.component';

describe('RatingStatsChartComponent', () => {
  let component: RatingStatsChartComponent;
  let fixture: ComponentFixture<RatingStatsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatingStatsChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingStatsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
