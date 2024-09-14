import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({  
  providedIn: 'root'
})

export class RansomwareService {   
  private apiUrl = 'http://localhost:8081/api/ransomware';

  constructor(private http: HttpClient) { } 
  getAllRansomware(page:number, size:number):Observable<any[]> {    
    const params = new HttpParams()
                    .set('page', page.toString())
                    .set('size', size.toString());
    return this.http.get<any[]>(this.apiUrl, { params });
  }
}