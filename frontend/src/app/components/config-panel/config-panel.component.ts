import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CodeGenerationContext } from '../../models/code-generation-context';
import { CodeGeneratorService } from '../../services/code-generator.service';

@Component({
  selector: 'app-config-panel',
  imports: [
    MatCardModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  templateUrl: './config-panel.component.html',
  styleUrl: './config-panel.component.scss'
})
export class ConfigPanelComponent {
  patternForm: FormGroup;
  @Output() codeGenerated = new EventEmitter<string>();

  constructor(private codeGeneratorService: CodeGeneratorService) {
    this.patternForm = new FormGroup({
      className: new FormControl('') 
    });
  }

  configure() {
    const context: CodeGenerationContext = {
      className: this.patternForm.get('className')?.value,
    };

    this.codeGeneratorService.generateCode('singleton', context).subscribe({
      next: (code) => this.generatedCode = code,
      error: (err) => console.error('Error generating code:', err)
    });
  }
}
