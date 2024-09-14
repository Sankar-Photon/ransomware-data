import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RansomwareTableComponent } from './ransomware-table.component';

describe('RansomwareTableComponent', () => {
  let component: RansomwareTableComponent;
  let fixture: ComponentFixture<RansomwareTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RansomwareTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RansomwareTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
