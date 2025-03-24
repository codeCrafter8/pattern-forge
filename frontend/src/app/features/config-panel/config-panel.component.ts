import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-config-panel',
  imports: [
    MatCardModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  templateUrl: './config-panel.component.html',
  styleUrl: './config-panel.component.scss'
})
export class ConfigPanelComponent {
  patternForm: FormGroup;

  constructor() {
    this.patternForm = new FormGroup({
      className: new FormControl('') 
    });
  }
}
