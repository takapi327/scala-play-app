import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ValidationMessages {
  email = [
    { type: 'required',   message: 'メールアドレスは必須項目です' },
    { type: 'email',      message: 'メールアドレスは正しい形式で入力してください' },
    { type: 'registered', message: 'このメールアドレスはすでに使われています'},
    { type: 'none',       message: 'このメールアドレスは登録されていません'}
  ];

  password = [
    { type: 'required',  message: 'パスワードは必須項目です' },
    { type: 'minlength', message: 'パスワードは8文字以上で入力してください' },
  ];
}
