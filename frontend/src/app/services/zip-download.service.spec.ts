import { TestBed } from '@angular/core/testing';

import { ZipDownloadService } from './zip-download.service';
import { provideHttpClient } from '@angular/common/http';

describe('ZipDownloadService', () => {
  let service: ZipDownloadService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()],
    });
    service = TestBed.inject(ZipDownloadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
