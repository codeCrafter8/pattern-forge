import { Component } from '@angular/core';
import { MatDialogRef, MatDialogContent, MatDialogActions, MatDialogTitle } from '@angular/material/dialog';
import { AiCodeGeneratorService } from '../../services/ai-code-generator.service';
import { MatFormField, MatLabel } from "@angular/material/form-field";
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-ai-generator-dialog',
  imports: [
    FormsModule,
    MatDialogContent, 
    MatFormField, 
    MatLabel, 
    MatDialogActions,
    MatInputModule,
    MatButtonModule,
    MatDialogTitle
  ],
  templateUrl: './ai-generator-dialog.component.html',
  styleUrl: './ai-generator-dialog.component.scss'
})
export class AiGeneratorDialogComponent {
  description = '';
  loading = false;

  constructor(
    private dialogRef: MatDialogRef<AiGeneratorDialogComponent>,
    private aiService: AiCodeGeneratorService
  ) {}

  generate(): void {
    if (!this.description.trim()) return;

    this.loading = true;

    this.aiService.generate({ problemDescription: this.description })
      .subscribe({
        next: response => this.dialogRef.close(response),
        error: err => {
          console.error(err);
          this.loading = false;
        }
      });
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
