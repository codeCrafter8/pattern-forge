import { TestBed } from '@angular/core/testing';

import { AiCodeGeneratorService } from './ai-code-generator.service';

describe('AiCodeGeneratorService', () => {
  let service: AiCodeGeneratorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AiCodeGeneratorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
