import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PatternService {
  private readonly API_URL = `${environment.baseUrl}/patterns`;
  
  constructor(private http: HttpClient) {}

  getPatternVariables(patternName: string): Observable<string[]> {
    return this.http.get<string[]>(`${this.API_URL}/${patternName}/config`);
  }
}
