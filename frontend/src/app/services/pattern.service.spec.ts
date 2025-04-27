import { TestBed } from '@angular/core/testing';

import { PatternService } from './pattern.service';
import { provideHttpClient } from '@angular/common/http';

describe('PatternService', () => {
  let service: PatternService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()],
    });
    service = TestBed.inject(PatternService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
