import { Component } from '@angular/core';
import { PatternMenuComponent } from "./components/pattern-menu/pattern-menu.component";
import { ConfigPanelComponent } from './components/config-panel/config-panel.component';
import { CodeDisplayComponent } from './components/code-display/code-display.component';
import { MatButtonModule } from '@angular/material/button';
import { GeneratedFile } from './models/generated-file';
import { NgIf, TitleCasePipe } from '@angular/common';
import { ZipDownloadService } from './services/zip-download.service';
import { MatDialog } from '@angular/material/dialog';
import { AiGeneratorDialogComponent } from './components/ai-generator-dialog/ai-generator-dialog.component';
import { MatIcon } from "@angular/material/icon";
import { AiPatternResponse } from './models/ai-pattern-response';

@Component({
  selector: 'app-root',
  imports: [
    PatternMenuComponent,
    ConfigPanelComponent,
    CodeDisplayComponent,
    MatButtonModule,
    TitleCasePipe,
    NgIf,
    MatIcon
],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  generatedFiles: GeneratedFile[] = [];
  selectedPattern: string = 'singleton';

  constructor(
    private zipDownloadService: ZipDownloadService,
    private dialog: MatDialog,
  ) {}
  
  handlePatternSelection(pattern: string) {
    this.selectedPattern = pattern;
    this.generatedFiles = []; 
  }

  handleFilesGenerated(files: GeneratedFile[]) {
    this.generatedFiles = files;
  }

  download() {
    this.zipDownloadService.downloadZip(this.generatedFiles).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        
        const a = document.createElement('a');
        a.href = url;
        a.download = 'pattern.zip'; 
        a.click();
        
        window.URL.revokeObjectURL(url);
      },
      error: (err) => {
        console.error('Error downloading ZIP:', err);
      }
    });
  }

  openAiDialog(): void {
    const dialogRef = this.dialog.open(AiGeneratorDialogComponent, {
      width: '700px'
    });

    dialogRef.afterClosed().subscribe((files) => {
      if (files) {
        this.generatedFiles = files;
      }
    });

    dialogRef.afterClosed().subscribe((aiResponse: AiPatternResponse) => {
      if (aiResponse && aiResponse.generatedFiles.length > 0) {
        this.generatedFiles = aiResponse.generatedFiles;
        this.selectedPattern = aiResponse.patternName || this.selectedPattern;
      }
    });
  }
}
