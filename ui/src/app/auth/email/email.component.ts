import { Component, OnInit, Input }    from '@angular/core';

import { ValidationMessages }   from '../validation-messages'
import { ValidationService }    from '../../service/validation.service';

@Component({
  selector:    'app-email',
  templateUrl: './email.component.html',
  styleUrls:   ['./email.component.scss']
})
export class EmailComponent implements OnInit {

  @Input() isSignup: boolean;

  constructor(
    public validationMessages: ValidationMessages,
    public validationService:  ValidationService
  ) {}

  ngOnInit(): void {}

  /**
   * 共通化処理をした場合にバリデーションの条件分岐が上手くいかなかったため、
   * 一旦保留
   */
  email = this.validationService.emailRegisteredNone();
}
