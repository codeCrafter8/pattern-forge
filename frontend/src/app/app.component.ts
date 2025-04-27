import { Component } from '@angular/core';
import { PatternMenuComponent } from "./components/pattern-menu/pattern-menu.component";
import { ConfigPanelComponent } from './components/config-panel/config-panel.component';
import { CodeDisplayComponent } from './components/code-display/code-display.component';
import { MatButtonModule } from '@angular/material/button';
import { GeneratedFile } from './models/generated-file';
import { TitleCasePipe } from '@angular/common';
import { ZipDownloadService } from './services/zip-download.service';

@Component({
  selector: 'app-root',
  imports: [
    PatternMenuComponent, 
    ConfigPanelComponent, 
    CodeDisplayComponent,
    MatButtonModule,
    TitleCasePipe
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  generatedFiles: GeneratedFile[] = [];
  selectedPattern: string = 'singleton';

  constructor(
    private zipDownloadService: ZipDownloadService,
  ) {}
  
  handlePatternSelection(pattern: string) {
    this.selectedPattern = pattern;
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
        a.download = 'generated_files.zip'; 
        a.click();
        
        window.URL.revokeObjectURL(url);
      },
      error: (err) => {
        console.error('Error downloading ZIP:', err);
      }
    });
  }
}
