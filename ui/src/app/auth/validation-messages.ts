import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ValidationMessages {

  phoneticFirst = [
    { type: 'required', message: '姓をフリガナで入力してください'},
    { type: 'pattern',  message: 'カタカナで入力してください'}
  ];

  phoneticLast = [
    { type: 'required', message: '名をフリガナで入力してください'},
    { type: 'pattern',  message: 'カタカナで入力してください'}
  ];

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
