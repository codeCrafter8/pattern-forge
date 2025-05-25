import { Component, EventEmitter, Output } from '@angular/core';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-pattern-menu',
  imports: [
    NgFor,
  ],
  templateUrl: './pattern-menu.component.html',
  styleUrl: './pattern-menu.component.scss'
})
export class PatternMenuComponent {
  patternTypes = [
    {
      name: 'Structural Patterns',
      patternNames: ['Adapter']
    },
    {
      name: 'Behavioral Patterns',
      patternNames: ['Observer']
    },
    {
      name: 'Creational Patterns',
      patternNames: ['Factory Method', 'Singleton']
    }
  ];
  @Output() patternSelected = new EventEmitter<string>();

  selectPattern(patternName: string) {
    this.patternSelected.emit(patternName.toLowerCase());
  }
}
