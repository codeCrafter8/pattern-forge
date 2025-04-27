import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
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
  @Output() filesGenerated = new EventEmitter<GeneratedFile[]>();

  constructor(
    private patternService: PatternService,
    private codeGeneratorService: CodeGeneratorService
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedPattern']) {
      this.loadVariablesForPattern(this.selectedPattern);
    }
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

  formatVariableName(variable: string): string {
    if (!variable) return variable;
  
    return variable
      .split('')
      .map((char, index) => {
        if (index === 0) {
          return char.toUpperCase(); 
        }
        return char === char.toUpperCase() ? ` ${char}` : char; 
      })
      .join('');
  }

  configure() {
    const formValues = this.patternForm.value;
    
    const context: CodeGenerationContext = {
      patternName: this.selectedPattern || '',
      ...formValues
    };

    this.codeGeneratorService.generateFiles(context).subscribe({
      next: (files: GeneratedFile[]) => {
        this.filesGenerated.emit(files); 
      },
      error: (err) => console.error('Error generating code:', err)
    });
  }
}
