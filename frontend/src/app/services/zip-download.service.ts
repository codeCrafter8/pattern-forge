import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZipDownloadService {
  private readonly API_URL = `${environment.baseUrl}/zip-generator`;

  constructor(private http: HttpClient) {}

  downloadZip(generatedFiles: { fileName: string, content: string }[]): Observable<Blob> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'  
    });

    return this.http.post<Blob>(`${this.API_URL}/download`, generatedFiles, {
        headers,
        responseType: 'blob' as 'json'  
    });
  }
}
