import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { User } from './interface/user';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: HttpClient) {}

  private authUrl = 'http://localhost:4200/api/auth';
    
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':    'application/json',
      'withCredentials': 'true',
    })
  };

  /**
   * HTTP POST メソッドを実行する
   * (認証機能、ログインをする場合のコード)
   *
   * @returns {Observable<User>}
   * @memberof HttpClientService
   */
  login(user: User): Observable<User> {
    return this.http.post<User>(`${this.authUrl}/login`, user, this.httpOptions);
  }
}
