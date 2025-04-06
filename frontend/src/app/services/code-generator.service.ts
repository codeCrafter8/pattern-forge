import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { CodeGenerationContext } from '../models/code-generation-context';
import { Observable } from 'rxjs/internal/Observable';
import { GeneratedFile } from '../models/generated-file';

@Injectable({
  providedIn: 'root'
})
export class CodeGeneratorService {
  private readonly API_URL = `${environment.baseUrl}/code-generator`;

  constructor(private http: HttpClient) {}

  generateFiles(pattern: string, context: CodeGenerationContext): Observable<GeneratedFile[]> {
    return this.http.post<GeneratedFile[]>(`${this.API_URL}/generate/${pattern}`, context);
  }
}
