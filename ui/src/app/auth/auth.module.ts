// ---- [ Module ] -----------------------------------------------
import { CommonModule }     from '@angular/common';
import { NgModule }         from '@angular/core';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// ---- [ Component ] -----------------------------------------------
import { LoginComponent }    from './login/login.component';
import { SignupComponent }   from './signup/signup.component';
import { LogoutComponent }   from './logout/logout.component';
import { EmailComponent }    from './email/email.component';
import { PasswordComponent } from './password/password.component';

// ---- [ Cookie ] -----------------------------------------------
import { CookieService } from 'ngx-cookie-service';

const COMPONENTS = [
  LoginComponent,
  SignupComponent,
  LogoutComponent,
  EmailComponent,
  PasswordComponent
]

const MODULES = [
  CommonModule,
  ReactiveFormsModule,
  FormsModule,
  HttpClientModule,
  HttpClientXsrfModule.withOptions({
    cookieName: 'My-Xsrf-Cookie'
  })
]

@NgModule({
  declarations: COMPONENTS,
  imports:      MODULES,
  exports:      COMPONENTS,
  providers:    [CookieService],
  bootstrap:    []
})
export class AuthModule { }

