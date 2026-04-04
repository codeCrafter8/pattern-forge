import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GeneratedFile } from '../models/generated-file';
import { AiPatternRequest } from '../models/ai-pattern-request';

@Injectable({
  providedIn: 'root'
})
export class AiCodeGeneratorService {
  private readonly API_URL = `${environment.baseUrl}/code-generator/ai`;

  constructor(private http: HttpClient) {}

  generate(request: AiPatternRequest): Observable<GeneratedFile[]> {
    return this.http.post<GeneratedFile[]>(
      `${this.API_URL}/generate`,
      request
    );
  }
}
