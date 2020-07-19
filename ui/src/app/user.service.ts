import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { User } from './interface/user';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  private userUrl = 'http://localhost:9000/api/auth'
    
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  loginUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.userUrl}/loginForm`, user)
  }

  constructor(private http: HttpClient) { }
}
