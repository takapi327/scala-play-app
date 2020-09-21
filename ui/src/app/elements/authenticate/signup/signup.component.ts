// ---- [ @angular ] ------------------------------------------------
import { Component, OnInit }                  from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { Router }                             from '@angular/router';

// ---- [ Interface ] -----------------------------------------------
import { Signup }               from '@modules/model/user';

// ---- [ Service ] -------------------------------------------------
import { AuthService }          from '@elements/service/auth.service';
import { ValidationService }    from '@elements/service/validation.service'
import { ValidationMessages }   from '../validation-messages';

@Component({
  selector:    'app-signup',
  templateUrl: './signup.component.html',
  styleUrls:   ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  constructor(
    private authService:        AuthService,
    private validationService:  ValidationService,
    private routes:             Router,
    public  validationMessages: ValidationMessages
  ) {}

  ngOnInit(): void {}

  firstName = new FormControl('', [
    Validators.required
  ]);

  lastName = new FormControl('', [
    Validators.required
  ]);

  email = new FormControl('',
    [
      Validators.required,
      Validators.email,
    ],
    this.validationService.emailRegisteredSome()
  );

  password = this.validationService.validatePassword()    

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
