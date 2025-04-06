import { TestBed } from '@angular/core/testing';

import { CodeGeneratorService } from './code-generator.service';
import { provideHttpClient } from '@angular/common/http';

describe('CodeGeneratorService', () => {
  let service: CodeGeneratorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()],
    });
    service = TestBed.inject(CodeGeneratorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
