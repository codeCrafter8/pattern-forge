import { TestBed } from '@angular/core/testing';

import { PatternSelectionService } from './pattern-selection.service';

describe('PatternSelectionService', () => {
  let service: PatternSelectionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatternSelectionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
