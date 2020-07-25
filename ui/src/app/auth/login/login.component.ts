// ---- [ @angular ] ------------------------------------------------
import { Component, OnInit }                  from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { Router }                             from '@angular/router';

// ---- [ AuthFunctin ] ---------------------------------------------
import { User }                               from '../../interface/user';
import { AuthService }                        from '../../service/auth.service'


@Component({
  selector:    'app-login',
  templateUrl: './login.component.html',
  styleUrls:   ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {}

  constructor(
    private authService: AuthService,
    private routes:      Router
  ) {}

  mail = new FormControl('', [
    Validators.required,
    Validators.email
  ]);

  password = new FormControl('', [
    Validators.required,
    Validators.minLength(3)
  ]);
  
  loginForm = new FormGroup({
    mail:     this.mail,
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
