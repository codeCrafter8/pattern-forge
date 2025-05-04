import { TestBed } from '@angular/core/testing';

import { ZipDownloadService } from './zip-download.service';

describe('ZipDownloadService', () => {
  let service: ZipDownloadService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZipDownloadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
