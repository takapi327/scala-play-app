import { Component }  from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { User } from './interface/user';
import { UserService } from './user.service'
import { AuthService } from './service/auth.service'

@Component({
  selector:    'app-root',
  templateUrl: './app.component.html',
  styleUrls:   ['./app.component.scss']
})
export class AppComponent {

  constructor(private authService: AuthService) {}

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
}
