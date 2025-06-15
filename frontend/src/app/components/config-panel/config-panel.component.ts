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
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';

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
    MatTooltipModule,
    MatSelectModule,
    MatCheckboxModule
  ],
  templateUrl: './config-panel.component.html',
  styleUrls: ['./config-panel.component.scss']
})
export class ConfigPanelComponent {
  availableLanguages = [
    { value: 'java', viewValue: 'Java' },
    { value: 'cpp', viewValue: 'C++' }
  ];

  patternForm: FormGroup = new FormGroup({
    language: new FormControl(this.availableLanguages[0].value, Validators.required)
  });

  singleVariables: string[] = [];
  repeatableVariables: string[] = [];
  groupedVariables: VariableGroup[] = [];
  booleanVariables: string[] = [];

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
        this.repeatableVariables = result.repeatableVariables || [];
        this.groupedVariables = result.groupedVariables || [];
        this.booleanVariables = result.booleanVariables || [];
        this.updateFormControls();
      },
      error: (err) => console.error('Failed to load pattern config:', err)
    });
  }

  updateFormControls(): void {
    this.singleVariables.forEach(variable => {
      if (!this.patternForm.contains(variable)) {
        this.patternForm.addControl(variable, new FormControl('', Validators.required));
      }
    });

    this.repeatableVariables.forEach(variable => {
      if (!this.patternForm.contains(variable)) {
        const formArray = new FormArray<FormControl>([new FormControl('', Validators.required)]);
        this.patternForm.addControl(variable, formArray);
      }
    });

    this.groupedVariables.forEach(group => {
      if (!this.patternForm.contains(group.groupName)) {
        const formArray = new FormArray<FormGroup>([]);
        formArray.push(this.createGroupInstance(group.variables));
        this.patternForm.addControl(group.groupName, formArray);
      }
    });

    this.booleanVariables.forEach(variable => {
      if (!this.patternForm.contains(variable)) {
        this.patternForm.addControl(variable, new FormControl(false)); 
      }
    });

    Object.keys(this.patternForm.controls).forEach(controlName => {
      if (
        controlName !== 'language' &&
        !this.singleVariables.includes(controlName) &&
        !this.repeatableVariables.includes(controlName) &&
        !this.groupedVariables.some(group => group.groupName === controlName) &&
        !this.booleanVariables.includes(controlName)
      ) {
        this.patternForm.removeControl(controlName);
      }
    });
  }

  getRepeatableFormArray(variableName: string): FormArray | null {
    const control = this.patternForm.get(variableName);
    return control instanceof FormArray ? control : null;
  }

  addRepeatableInstance(variableName: string): void {
    const formArray = this.getRepeatableFormArray(variableName);
    if (formArray) {
      formArray.push(new FormControl('', Validators.required));
    }
  }

  removeRepeatableInstance(variableName: string, index: number): void {
    const formArray = this.getRepeatableFormArray(variableName);
    if (formArray && formArray.length > 1) {
      formArray.removeAt(index);
    }
  }

  createGroupInstance(variables: string[]): FormGroup {
    const group = new FormGroup({});
    variables.forEach(variable => {
      group.addControl(variable, new FormControl('', Validators.required));
    });
    return group;
  }

  getGroupFormArray(groupName: string): FormArray | null {
    const control = this.patternForm.get(groupName);
    return control instanceof FormArray ? control : null;
  }

  addGroupInstance(group: VariableGroup): void {
    const formArray = this.getGroupFormArray(group.groupName);
    if (formArray) {
      formArray.push(this.createGroupInstance(group.variables));
    }
  }

  removeGroupInstance(groupName: string, index: number): void {
    const formArray = this.getGroupFormArray(groupName);
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