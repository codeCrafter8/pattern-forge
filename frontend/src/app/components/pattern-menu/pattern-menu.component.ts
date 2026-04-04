import { Component, EventEmitter, Output } from '@angular/core';
import { NgFor } from '@angular/common';
import { AVAILABLE_PATTERNS } from '../../constants/pattern-constants';

@Component({
  selector: 'app-pattern-menu',
  imports: [
    NgFor,
  ],
  templateUrl: './pattern-menu.component.html',
  styleUrl: './pattern-menu.component.scss'
})
export class PatternMenuComponent {
  patternTypes = AVAILABLE_PATTERNS;
  
  @Output() patternSelected = new EventEmitter<string>();

  selectPattern(patternName: string) {
    this.patternSelected.emit(patternName.toLowerCase());
  }
}
