// ---- [ @angular ] ------------------------------------------------
import { Component, OnInit }                  from '@angular/core';
import { Router }                             from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

// ---- [ Interface ] -----------------------------------------------
import { User }                 from '../../../interface/user';

// ---- [ Service ] -------------------------------------------------
import { AuthService }          from '../../service/auth.service';
import { ValidationService }    from '../../service/validation.service';
import { ValidationMessages }   from '../validation-messages';

@Component({
  selector:    'app-login',
  templateUrl: './login.component.html',
  styleUrls:   ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {}

  constructor(
    private authService:        AuthService,
    private validationService:  ValidationService,
    private routes:             Router,
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
    this.routes.navigate(['/']);
  }
}
