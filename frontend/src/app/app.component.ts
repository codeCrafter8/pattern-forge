import { Component } from '@angular/core';
import { PatternMenuComponent } from "./components/pattern-menu/pattern-menu.component";
import { ConfigPanelComponent } from './components/config-panel/config-panel.component';
import { CodeDisplayComponent } from './components/code-display/code-display.component';
import { MatButtonModule } from '@angular/material/button';
import { GeneratedFile } from './models/generated-file';
import { PatternSelectionService } from './services/pattern-selection.service';
import { Subscription } from 'rxjs';
import { TitleCasePipe } from '@angular/common';

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
  private subscription!: Subscription;
  selectedPattern: string = 'singleton';

  constructor(private patternSelectionService: PatternSelectionService) {}
  
  ngOnInit(): void {
    this.subscription = this.patternSelectionService.selectedPattern$.subscribe(pattern => {
      if (pattern) {
        this.selectedPattern = pattern;
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }

  handleGeneratedFiles(files: GeneratedFile[]) {
    this.generatedFiles = files;
  }

  download() {
    console.log('Generating code...');
  }
}
