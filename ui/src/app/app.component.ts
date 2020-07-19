import { Component }  from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector:    'app-root',
  templateUrl: './app.component.html',
  styleUrls:   ['./app.component.scss']
})
export class AppComponent {

  constructor() {}

  mail = new FormControl('', [
    Validators.required,
    Validators.email
  ]);

  pass = new FormControl('', [
    Validators.required,
    Validators.minLength(3)
  ]);
  
  loginForm = new FormGroup({
    mail: this.mail,
    pass: this.pass
  });

 }
