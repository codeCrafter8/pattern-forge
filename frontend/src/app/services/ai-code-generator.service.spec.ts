import { TestBed } from '@angular/core/testing';
import { AiCodeGeneratorService } from './ai-code-generator.service';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('AiCodeGeneratorService', () => {
  let service: AiCodeGeneratorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting()
      ]
      });
    service = TestBed.inject(AiCodeGeneratorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
