// ---- [ @angular ] ------------------------------------------------
import { Component, OnInit }                  from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

// ---- [ Interface ] -----------------------------------------------
import { User }                 from '@modules/model/user';

// ---- [ Service ] -------------------------------------------------
import { AuthService }          from '@elements/service/auth.service';
import { ValidationService }    from '@elements/service/validation.service';
import { ValidationMessages }   from '../validation-messages';

@Component({
  selector:    'login-form',
  templateUrl: './login.component.html',
  styleUrls:   ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {}

  constructor(
    private authService:        AuthService,
    private validationService:  ValidationService,
    public  validationMessages: ValidationMessages
  ) {}

  email = new FormControl('',
    [
      Validators.required,
      Validators.email,
    ],
    this.validationService.emailRegisteredNone()
  );

  password = this.validationService.validatePassword()  

  loginForm = new FormGroup({
    email:    this.email,
    password: this.password
  });

  login(user: User): void {
    this.authService.login(user).subscribe()
  }
  
  onSubmit() {
    this.login(this.loginForm.value);
  }
}
