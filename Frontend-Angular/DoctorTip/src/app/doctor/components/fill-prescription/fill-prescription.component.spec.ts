import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FillPrescriptionComponent } from './fill-prescription.component';

describe('FillPrescriptionComponent', () => {
  let component: FillPrescriptionComponent;
  let fixture: ComponentFixture<FillPrescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FillPrescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FillPrescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
