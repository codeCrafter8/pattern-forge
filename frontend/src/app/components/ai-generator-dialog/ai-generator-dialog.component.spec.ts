import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AiGeneratorDialogComponent } from './ai-generator-dialog.component';
import { MatDialogRef } from '@angular/material/dialog';
import { AiCodeGeneratorService } from '../../services/ai-code-generator.service';
import { of } from 'rxjs';

describe('AiGeneratorDialogComponent', () => {
  let component: AiGeneratorDialogComponent;
  let fixture: ComponentFixture<AiGeneratorDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AiGeneratorDialogComponent],
      providers: [
        { provide: MatDialogRef, useValue: { close: () => {} } },
        { provide: AiCodeGeneratorService, useValue: { generate: () => of({}) } }
      ]
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
