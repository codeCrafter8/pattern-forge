import { Component } from '@angular/core';
import { HomeComponent } from "./features/home/home.component";
import { PatternMenuComponent } from "./shared/pattern-menu/pattern-menu.component";

@Component({
  selector: 'app-root',
  imports: [HomeComponent, PatternMenuComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
}
