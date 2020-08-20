import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ValidationMessages {
  email = [
    { type: 'required', message: 'メールアドレスは必須項目です' },
    { type: 'email',    message: 'メールアドレスは正しい形式で入力してください' }
  ];
}
