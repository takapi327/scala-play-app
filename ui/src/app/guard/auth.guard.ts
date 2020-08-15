import { Injectable }                           from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot,
         RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable }                           from 'rxjs';

import { AuthService } from '../service/auth.service';
import { Auth } from '../interface/user'

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router:      Router
  ) { }

  isAuth: Auth;
  /**
   *
   * @param next 遷移先の Route の状態を表すイミュータブルオブジェクト
   *             URLのセグメント情報やクエリパラメータ、フラグメントパラメータを取得
   * @param state 遷移先の Router の状態を表すイミュータブルオブジェクト
   * @return Observable<boolean> | Promise<boolean> | boolean
   */
  canActivate(
    next:  ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    this.authService.authenticate().subscribe(
      auth => {
        this.isAuth = auth
      }
    );

    if (this.isAuth) {
      return true
    }
    alert("ログインしていません")
    this.router.navigate(['/']);
    return false;
  }
}
