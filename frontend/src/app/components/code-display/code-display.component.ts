import { Component, Input } from '@angular/core'; 
import { MatCardModule } from '@angular/material/card'; 
import { MatTabsModule } from '@angular/material/tabs'; 
import { GeneratedFile } from '../../models/generated-file';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-code-display',
  imports: [
    MatCardModule,
    MatTabsModule,
    NgFor
  ],
  templateUrl: './code-display.component.html',
  styleUrl: './code-display.component.scss'
})
export class CodeDisplayComponent {
  @Input() generatedFiles: GeneratedFile[] = [];

}
