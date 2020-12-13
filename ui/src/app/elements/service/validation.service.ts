import { Injectable }              from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AsyncValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';

import { Observable }  from 'rxjs';
import { map }         from 'rxjs/operators';

import { AuthService } from './auth.service'

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor(
    private authService: AuthService
  ) {}

  // ---- [ variable ] -------------
  /**
   *
   * メールアドレスのバリデーション
   * @export Email Signup Component
   *
   * メールアドレスを共通化した場合の処理分けができなかったため、
   * 一旦保留
   *
  signupEmail = new FormControl('',
    [
      Validators.required,
      Validators.email,
    ],
    this.emailRegisteredSome()
  );
  loginEmail = new FormControl('',
    [
      Validators.required,
      Validators.email,
    ],
    this.emailRegisteredNone()
  );
  */

  /**
   *
   * パスワードのバリデーション
   * @export password Login Signup Component
   */
  password = new FormControl('', [
    Validators.required,
    Validators.minLength(8)
  ]);

  // ---- [ method ] ----------------
  /*
   * メールアドレスを共通化した場合の処理分けができなかったため、
   * 一旦保留
   *
  validateSignup(): FormControl {
    return this.signupEmail
  }
  */
  validatePassword(): FormControl {
    return this.password
  }

  /**
   *
   * メールアドレスが登録済みかどうかののバリデーション
   * @export email Signup Component
   */
  emailRegisteredSome(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.authService.isEmailRegistered({email: control.value}).pipe(
        map(email => {
          return email.isRegistered ? { registered: true } : null;
        })
      )
    }
  }
  /**
   *
   * メールアドレスが登録済みかどうかののバリデーション
   * @export email Login Component
   */
  emailRegisteredNone(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.authService.isEmailRegistered({email: control.value}).pipe(
        map(email => {
          return email.isRegistered ? null : { none: true };
        })
      )
    }
  }
}
