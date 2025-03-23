import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-config-panel',
  imports: [
    MatCardModule,
    ReactiveFormsModule,
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
