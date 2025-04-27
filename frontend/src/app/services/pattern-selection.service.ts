import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PatternSelectionService {
  private selectedPatternSubject = new BehaviorSubject<string | null>(null);
  selectedPattern$ = this.selectedPatternSubject.asObservable();

  selectPattern(patternName: string) {
    this.selectedPatternSubject.next(patternName);
  }
}
