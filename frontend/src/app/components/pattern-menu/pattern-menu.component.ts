import { Component } from '@angular/core';
import { NgFor } from '@angular/common';
import { PatternSelectionService } from '../../services/pattern-selection.service';

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
      patternNames: ['Factory', 'Singleton']
    }
  ];

  constructor(private patternSelectionService: PatternSelectionService) {}

  selectPattern(patternName: string) {
    this.patternSelectionService.selectPattern(patternName.toLowerCase());
  }
  
}
