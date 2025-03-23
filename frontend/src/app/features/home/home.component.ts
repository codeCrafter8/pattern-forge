import { Component } from '@angular/core';
import { ConfigPanelComponent } from "../config-panel/config-panel.component";
import { CodeDisplayComponent } from "../code-display/code-display.component";
import { MatButtonModule } from '@angular/material/button'; 

@Component({
  selector: 'app-home',
  imports: [
    ConfigPanelComponent, 
    CodeDisplayComponent,
    MatButtonModule,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  generateCode() {
    console.log('Generating code...');
  }
}
