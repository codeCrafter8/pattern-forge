import { Component } from '@angular/core';
import { PatternMenuComponent } from "./components/pattern-menu/pattern-menu.component";
import { ConfigPanelComponent } from './components/config-panel/config-panel.component';
import { CodeDisplayComponent } from './components/code-display/code-display.component';
import { MatButtonModule } from '@angular/material/button';
import { GeneratedFile } from './models/generated-file';

@Component({
  selector: 'app-root',
  imports: [
    PatternMenuComponent, 
    ConfigPanelComponent, 
    CodeDisplayComponent,
    MatButtonModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
  generatedFiles: GeneratedFile[] = [];

  handleGeneratedFiles(files: GeneratedFile[]) {
    this.generatedFiles = files;
  }

  download() {
    console.log('Generating code...');
  }
}
