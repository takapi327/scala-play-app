import { Component }  from '@angular/core';
import {FormControl, Validators, FormGroup, FormBuilder} from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector:    'app-root',
  templateUrl: './app.component.html',
  styleUrls:   ['./app.component.scss']
})
export class AppComponent {

  constructor(
    private http:    HttpClient,
    private builder: FormBuilder
  ) {}

  mail = new FormControl('', [
    Validators.required,
    Validators.email
  ]);

  pass = new FormControl('', [
    Validators.required,
    Validators.minLength(3)
  ]);
  
  myForm = this.builder.group({
    mail: this.mail,
    pass: this.pass
  });

  ngSubmit() {
    this.http.post('http://localhost:9000/auth/loginForm').subscribe(
    );
    }
}
