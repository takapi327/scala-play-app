import { Injectable }              from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor() { }

  // ---- [ variable ] -------------
  /**
   *
   * メールアドレスのバリデーション
   * @export Email Login Signup Component
   */
  email = new FormControl('', [
    Validators.required,
    Validators.email
  ]);
  
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
  isEmailValidation(): FormControl {
    return this.email
  }
  
  isPasswordValidation(): FormControl {
    return this.password
  }
}
