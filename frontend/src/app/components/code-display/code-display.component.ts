import { Component, Input, OnChanges, SimpleChanges } from '@angular/core'; 
import { MatCardModule } from '@angular/material/card'; 
import { MatTabsModule } from '@angular/material/tabs'; 
import { GeneratedFile } from '../../models/generated-file';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MonacoEditorModule } from 'ngx-monaco-editor-v2';

@Component({
  selector: 'app-code-display',
  imports: [
    MatCardModule,
    MatTabsModule,
    NgFor,
    NgIf,
    FormsModule,
    MonacoEditorModule
  ],
  templateUrl: './code-display.component.html',
  styleUrl: './code-display.component.scss'
})
export class CodeDisplayComponent implements OnChanges {
  @Input() generatedFiles: GeneratedFile[] = [];

  fileOptions: { [fileName: string]: any } = {};

  baseEditorOptions = {
    theme: 'vs-dark',
    automaticLayout: true,
    scrollBeyondLastLine: false,
    minimap: { enabled: false },
    fontSize: 14,
  };

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['generatedFiles'] && this.generatedFiles) {
      this.prepareOptions();
    }
  }

  private prepareOptions() {
    this.fileOptions = {}; 
    this.generatedFiles.forEach(file => {
      this.fileOptions[file.fileName] = {
        ...this.baseEditorOptions,
        language: this.getLanguageMode(file.fileName)
      };
    });
  }

  private getLanguageMode(filename: string): string {
    if (!filename) return 'plaintext';
    if (filename.endsWith('.java')) return 'java';
    if (filename.endsWith('.cpp')) return 'cpp';
    return 'plaintext';
  }
}
