import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User, Signup, Email } from '@modules/model/user';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  /**
   * コンストラクタ. HttpClientService のインスタンスを生成する
   *
   * @param {Http} Httpサービスを DI する
   * @memberof HttpClientService
   */
  constructor(private http: HttpClient) {}

  /**
   * REST-API 実行時に指定する URL
   *
   * バックエンドは PlayFramework で実装し、ポート番号「9000」で待ち受けているため、
   * そのまま指定すると CORS でエラーになる
   * それを回避するため、ここではフロントエンドのポート番号「4200」を指定し、
   * Angular CLI のリバースプロキシを利用してバックエンドとの通信を実現させる
   *
   * 認証専用の URL を設定、バックエンドのapi/がフロントエンドとの連携用に設定してある
   *
   * @private
   * @memberof HttpClientService
   */
  private authUrl = 'http://localhost:4200/api/v1/auth';

  /**
   *
   *
   */
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
   * @param url    ログインのエンドポイント
   * @param User   ログイン用の入力データ
   * @return        An `Observable` of the response body as a `string`.
   * @memberof     HttpClientService
   */
  login(user: User): Observable<{ fullName: string }> {
    return this.http.post<{ fullName: string }>(`${this.authUrl}/login`, user, this.httpOptions);
  }

  /**
   * HTTP POST メソッドを実行する
   * (認証機能、会員登録をする場合のコード)
   *
   * @param url    会員登録用のエンドポイント
   * @param Signup 会員登録用の入力データ
   * @return       An `Observable` of the response body as a `{string, string}`.
   * @memberof     HttpClientService
   */
  signup(signup: Signup): Observable<{ fullName: string, email: string }> {
    return this.http.post<{ fullName: string, email: string }>(`${this.authUrl}/signup`, signup, this.httpOptions);
  }

  /**
   * HTTP DELETE メソッドを実行する
   * (認証機能、ログアウトをする場合のコード)
   *
   * @return     An `Observable` of the response body as a `any`.
   * @param url  ログアウト用のエンドポイント
   * @memberof   HttpClientService
   */
  logout(): Observable<any> {
    return this.http.delete<any>(`${this.authUrl}/logout`, this.httpOptions);
  }

  /**
   * HTTP GET メソッドを実行する
   * (認証,認可用のコード)
   *
   * @return    An `Observable` of the respon:se body as a `{ isAuth: boolean }`.
   * @param url 認証、認可用のエンドポイント
   * @memberof  HttpClientService
   */
  isAuthenticate(): Observable<{ isAuth: boolean }> {
    return this.http.get< {isAuth: boolean} >(`${this.authUrl}/isAuthenticate`, this.httpOptions);
  }

  /**
   * HTTP POST メソッドを実行する
   * (Emailが登録済みかどうかを確認するコード)
   *
   * @return    An `Observable` of the response body as a `{ isRegistered: boolean }`.
   * @param url Emailが登録済みかどうかを確認するエンドポイント
   * @memberof  HttpClientService
   */
  isEmailRegistered(payload: {email: string}): Observable<{ isRegistered: boolean }> {
    return this.http.post<{ isRegistered: boolean }>(`${this.authUrl}/isEmailRegistered`, payload, this.httpOptions);
  }
}
