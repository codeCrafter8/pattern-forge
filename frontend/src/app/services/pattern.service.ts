import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { VariableExtractionResult } from '../models/variable-extraction-result';

@Injectable({ providedIn: 'root' })
export class PatternService {
  private readonly API_URL = `${environment.baseUrl}/patterns`;
  
  constructor(private http: HttpClient) {}

  getPatternVariables(patternName: string): Observable<VariableExtractionResult> {
    return this.http.get<VariableExtractionResult>(`${this.API_URL}/${patternName}/config`);
  }
}
