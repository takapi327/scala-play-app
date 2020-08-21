import { Component, OnInit }    from '@angular/core';

import { ValidationMessages }   from '../validation-messages'
import { ValidationService }    from '../../service/validation.service';

@Component({
  selector:    'app-password',
  templateUrl: './password.component.html',
  styleUrls:   ['./password.component.scss']
})
export class PasswordComponent implements OnInit {

  constructor(
    public validationMessages: ValidationMessages,
    public validationService:  ValidationService
  ) { }

  ngOnInit(): void {
  }

  password = this.validationService.validatePassword()
}
