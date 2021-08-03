import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FillConcernsComponent } from './fill-concerns.component';

describe('FillConcernsComponent', () => {
  let component: FillConcernsComponent;
  let fixture: ComponentFixture<FillConcernsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FillConcernsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FillConcernsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
