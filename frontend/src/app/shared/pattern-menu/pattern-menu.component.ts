import { Component } from '@angular/core';
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
  patterns = [
    {
      name: 'Wzorce Strukturalne',
      submenu: ['Builder']
    },
    {
      name: 'Wzorce Behawioralne',
      submenu: ['Observer']
    },
    {
      name: 'Wzorce Kreacyjne',
      submenu: ['Factory', 'Singleton']
    }
  ];
}
