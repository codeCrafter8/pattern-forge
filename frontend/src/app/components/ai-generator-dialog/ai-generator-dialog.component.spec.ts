import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AiGeneratorDialogComponent } from './ai-generator-dialog.component';

describe('AiGeneratorDialogComponent', () => {
  let component: AiGeneratorDialogComponent;
  let fixture: ComponentFixture<AiGeneratorDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AiGeneratorDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AiGeneratorDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
