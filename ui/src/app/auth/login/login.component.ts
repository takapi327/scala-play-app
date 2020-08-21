// ---- [ @angular ] ------------------------------------------------
import { Component, OnInit }         from '@angular/core';
import { Router }                    from '@angular/router';
import { FormControl, Validators, FormGroup }  from '@angular/forms';

// ---- [ Interface ] -----------------------------------------------
import { User }                 from '../../interface/user';

// ---- [ Service ] -------------------------------------------------
import { AuthService }          from '../../service/auth.service';
import { ValidationService }    from '../../service/validation.service'
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

  loginForm = new FormGroup({
    email:    this.validationService.validateEmail(),
    password: this.validationService.validatePassword()
  });

  login(user: User): void {
    this.authService.login(user).subscribe()
  }
  
  onSubmit() {
    this.login(this.loginForm.value);
    this.routes.navigate(['/']);
  }
}
