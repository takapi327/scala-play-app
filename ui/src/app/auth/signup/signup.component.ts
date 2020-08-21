// ---- [ @angular ] ------------------------------------------------
import { Component, OnInit }                  from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { Router }                             from '@angular/router';

// ---- [ Interface ] -----------------------------------------------
import { Signup }               from '../../interface/user';

// ---- [ Service ] -------------------------------------------------
import { AuthService }          from '../../service/auth.service';
import { ValidationService }    from '../../service/validation.service'
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

  ngOnInit(): void {
  }

  firstName = new FormControl('', [
    Validators.required
  ]);

  lastName = new FormControl('', [
    Validators.required
  ]);

  signupForm = new FormGroup({
    firstName: this.firstName,
    lastName:  this.lastName,
    email:     this.validationService.validateEmail(),
    password:  this.validationService.validatePassword()
  });

  signup(signup: Signup): void {
    this.authService.signup(signup).subscribe()
  }

  onSubmit() {
    this.signup(this.signupForm.value);
    this.routes.navigate(['/']);
  }
}
