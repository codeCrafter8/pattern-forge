import { Component, Input } from '@angular/core'; 
import { MatCardModule } from '@angular/material/card'; 
import { MatTabsModule } from '@angular/material/tabs'; 

@Component({
  selector: 'app-code-display',
  imports: [
    MatCardModule,
    MatTabsModule
  ],
  templateUrl: './code-display.component.html',
  styleUrl: './code-display.component.scss'
})
export class CodeDisplayComponent {
  //baseClassCode = 'class BaseClass {\n  constructor() {\n    // base class logic\n  }\n}';
  @Input() baseClassCode: string = '';
  
  childClassCode = 'class ChildClass extends BaseClass {\n  constructor() {\n    super();\n    // child class logic\n  }\n}';

}
