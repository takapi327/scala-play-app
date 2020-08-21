import { Component, OnInit }    from '@angular/core';

import { ValidationMessages }   from '../validation-messages'
import { ValidationService }    from '../../service/validation.service';

@Component({
  selector:    'app-email',
  templateUrl: './email.component.html',
  styleUrls:   ['./email.component.scss']
})
export class EmailComponent implements OnInit {

  constructor(
    public validationMessages: ValidationMessages,
    public validationService:  ValidationService
  ) {}

  ngOnInit(): void {
  }

  email = this.validationService.isEmailValidation()
}
