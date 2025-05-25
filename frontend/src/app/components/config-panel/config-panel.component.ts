import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormArray, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CodeGenerationContext } from '../../models/code-generation-context';
import { CodeGeneratorService } from '../../services/code-generator.service';
import { GeneratedFile } from '../../models/generated-file';
import { PatternService } from '../../services/pattern.service';
import { NgFor, NgIf } from '@angular/common';
import { VariableGroup } from '../../models/variable-group';
import { VariableExtractionResult } from '../../models/variable-extraction-result';
import { MatTabsModule } from '@angular/material/tabs';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-config-panel',
  standalone: true,
  imports: [
    MatCardModule,
    ReactiveFormsModule,
    MatButtonModule,
    NgFor,
    NgIf,
    MatTabsModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatTooltipModule
  ],
  templateUrl: './config-panel.component.html',
  styleUrls: ['./config-panel.component.scss']
})
export class ConfigPanelComponent {
  patternForm: FormGroup = new FormGroup({});
  singleVariables: string[] = [];
  groupedVariables: VariableGroup[] = [];
  @Input() selectedPattern: string = '';
  @Output() filesGenerated = new EventEmitter<GeneratedFile[]>();

  constructor(
    private patternService: PatternService,
    private codeGeneratorService: CodeGeneratorService
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedPattern'] && this.selectedPattern) {
      this.loadVariablesForPattern(this.selectedPattern);
    }
  }

  loadVariablesForPattern(pattern: string): void {
    this.patternService.getPatternVariables(pattern).subscribe({
      next: (result: VariableExtractionResult) => {
        this.singleVariables = result.singleVariables;
        this.groupedVariables = result.groupedVariables;
        this.updateFormControls();
      },
      error: (err) => console.error('Failed to load pattern config:', err)
    });
  }

  updateFormControls(): void {
    // Update single variables
    this.singleVariables.forEach(variable => {
      if (!this.patternForm.contains(variable)) {
        this.patternForm.addControl(variable, new FormControl('', Validators.required));
      }
    });

    // Update grouped variables
    this.groupedVariables.forEach(group => {
      if (!this.patternForm.contains(group.groupName)) {
        const formArray = new FormArray<FormGroup>([]);
        formArray.push(this.createGroupInstance(group.variables));
        this.patternForm.addControl(group.groupName, formArray);
      }
    });

    // Remove unused controls
    Object.keys(this.patternForm.controls).forEach(controlName => {
      if (!this.singleVariables.includes(controlName) && 
          !this.groupedVariables.some(group => group.groupName === controlName)) {
        this.patternForm.removeControl(controlName);
      }
    });
  }

  createGroupInstance(variables: string[]): FormGroup {
    const group = new FormGroup({});
    variables.forEach(variable => {
      group.addControl(variable, new FormControl('', Validators.required));
    });
    return group;
  }

  getFormArray(groupName: string): FormArray | null {
    const control = this.patternForm.get(groupName);
    return control instanceof FormArray ? control : null;
  }

  addGroupInstance(group: VariableGroup): void {
    const formArray = this.getFormArray(group.groupName);
    if (formArray) {
      formArray.push(this.createGroupInstance(group.variables));
    }
  }

  removeGroupInstance(groupName: string, index: number): void {
    const formArray = this.getFormArray(groupName);
    if (formArray && formArray.length > 1) { 
      formArray.removeAt(index);
    }
  }

  formatName(name: string): string {
    if (!name) return name;
    return name
      .replace(/([A-Z])/g, ' $1')
      .replace(/^./, str => str.toUpperCase())
      .trim();
  }

  configure(): void {
    if (this.patternForm.invalid) {
      this.markAllAsTouched();
      return;
    }

    console.log(this.patternForm.value);

    const context: CodeGenerationContext = {
      patternName: this.selectedPattern,
      ...this.patternForm.value
    };

    this.codeGeneratorService.generateFiles(context).subscribe({
      next: (files: GeneratedFile[]) => this.filesGenerated.emit(files),
      error: (err) => console.error('Error generating code:', err)
    });
  }

  private markAllAsTouched(): void {
    Object.values(this.patternForm.controls).forEach(control => {
      if (control instanceof FormGroup || control instanceof FormArray) {
        control.markAllAsTouched();
      } else {
        control.markAsTouched();
      }
    });
  }
}