// ---- [ @angular ] ------------------------------------------------
import { Component, OnInit }         from '@angular/core';
import { Router }                    from '@angular/router';
import { FormControl, Validators, FormGroup }  from '@angular/forms';

// ---- [ AuthFunctin ] ---------------------------------------------
import { User }                      from '../../interface/user';
import { AuthService }               from '../../service/auth.service'
import { ValidationMessages }        from '../validation-messages'


@Component({
  selector:    'app-login',
  templateUrl: './login.component.html',
  styleUrls:   ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {}

  constructor(
    private authService:        AuthService,
    private routes:             Router,
    public  validationMessages: ValidationMessages
  ) {}

  email = new FormControl('', [
    Validators.required,
    Validators.email
  ]);

  password = new FormControl('', [
    Validators.required,
    Validators.minLength(8)
  ]);

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
