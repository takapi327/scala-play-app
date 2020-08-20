// ---- [ @angular ] ------------------------------------------------
import { Component, OnInit }                  from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { Router }                             from '@angular/router';

// ---- [ AuthFunctin ] ---------------------------------------------
import { Signup }                             from '../../interface/user';
import { AuthService }                        from '../../service/auth.service'
import { ValidationMessages }                 from '../validation-messages'

@Component({
  selector:    'app-signup',
  templateUrl: './signup.component.html',
  styleUrls:   ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  constructor(
    private authService:        AuthService,
    private routes:             Router,
    public  validationMessages: ValidationMessages
  ) {}

  ngOnInit(): void {
  }


  firstName = new FormControl('', [
    Validators.required
  ]);

  lastName = new FormControl('', [
    Validators.required
  ]);

  email = new FormControl('', [
    Validators.required,
    Validators.email
  ]);

  password = new FormControl('', [
    Validators.required,
    Validators.minLength(8)
  ]);

  signupForm = new FormGroup({
    firstName: this.firstName,
    lastName:  this.lastName,
    email:     this.email,
    password:  this.password
  });

  signup(signup: Signup): void {
    this.authService.signup(signup).subscribe()
  }

  onSubmit() {
    this.signup(this.signupForm.value);
    this.routes.navigate(['/']);
  }
}
