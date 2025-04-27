import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CodeGenerationContext } from '../../models/code-generation-context';
import { CodeGeneratorService } from '../../services/code-generator.service';
import { GeneratedFile } from '../../models/generated-file';
import { PatternService } from '../../services/pattern.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-config-panel',
  imports: [
    MatCardModule,
    ReactiveFormsModule,
    MatButtonModule,
    NgFor
  ],
  templateUrl: './config-panel.component.html',
  styleUrl: './config-panel.component.scss'
})
export class ConfigPanelComponent {
  patternForm: FormGroup = new FormGroup({});
  variables: string[] = [];
  @Input() selectedPattern: string = '';
  @Output() generatedFiles = new EventEmitter<GeneratedFile[]>();

  constructor(
    private patternService: PatternService,
    private codeGeneratorService: CodeGeneratorService
  ) {}

  ngOnInit(): void {
    this.loadVariablesForPattern(this.selectedPattern);
  }

  loadVariablesForPattern(pattern: string) {
    this.patternService.getPatternVariables(pattern).subscribe({
      next: (vars) => {
        this.variables = vars;
        this.updateFormControls(vars);
      },
      error: (err) => console.error('Failed to load pattern config:', err)
    });
  }

  updateFormControls(variables: string[]) {
    for (const variable of variables) {
      if (!this.patternForm.contains(variable)) {
        this.patternForm.addControl(variable, new FormControl(''));
      }
    }

    for (const controlName of Object.keys(this.patternForm.controls)) {
      if (!variables.includes(controlName)) {
        this.patternForm.removeControl(controlName);
      }
    }
  }

  configure() {
    const context: CodeGenerationContext = {
      patternName: this.selectedPattern || '',
      className: this.patternForm.get('className')?.value,
    };

    this.codeGeneratorService.generateFiles(context).subscribe({
      next: (files: GeneratedFile[]) => {
        this.generatedFiles.emit(files); 
      },
      error: (err) => console.error('Error generating code:', err)
    });
  }
}
