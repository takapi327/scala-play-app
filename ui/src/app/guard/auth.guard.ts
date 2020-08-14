import { Injectable }                           from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot,
         RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable }                           from 'rxjs';

import { AuthService } from '../service/auth.service'

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  isAuth: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  getAuth(): void {
    this.authService.authenticate().subscribe(
      res => this.isAuth = res
    );
  }

  canActivate(
    next:  ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

/*
    this.authService.authenticate().subscribe(
      res => console.log(res)
    );
*/
    this.getAuth()
    if (this.isAuth) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
