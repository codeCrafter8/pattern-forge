import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatternMenuComponent } from './pattern-menu.component';

describe('PatternMenuComponent', () => {
  let component: PatternMenuComponent;
  let fixture: ComponentFixture<PatternMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatternMenuComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatternMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
