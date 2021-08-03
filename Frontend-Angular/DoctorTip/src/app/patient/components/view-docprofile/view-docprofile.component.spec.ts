import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDocprofileComponent } from './view-docprofile.component';

describe('ViewDocprofileComponent', () => {
  let component: ViewDocprofileComponent;
  let fixture: ComponentFixture<ViewDocprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewDocprofileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDocprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
