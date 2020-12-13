import { Injectable }          from '@angular/core';
import { CanActivate, Router } from '@angular/router';

import { AuthService } from '@elements/service/auth.service';
import { Auth }        from '@modules/model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router:      Router
  ) { }

  /**
   * @Auth boolean
   */
  isAuth: Auth;
  /**
   *
   *
   * ActivatedRouteSnapshotをimportして使用することも可能
   * -> 遷移先の Route の状態を表すイミュータブルオブジェクト
   *    URLのセグメント情報やクエリパラメータ、フラグメントパラメータを取得
   *
   * RouterStateSnapshotをimportして使用することも可能
   * -> 遷移先の Router の状態を表すイミュータブルオブジェクト
   *
   * @return boolean
   *
   */
  canActivate(): boolean{

    this.authService.isAuthenticate().subscribe(
      auth => {
        this.isAuth = auth
      }
    );

    if (this.isAuth) {
      return true
    } else {
      alert("ログインしていません")
      this.router.navigate(['/']);
      return false;
    }
  }
}
