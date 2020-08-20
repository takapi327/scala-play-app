// ---- [ Module ] -----------------------------------------------
import { CommonModule }     from '@angular/common';
import { NgModule }         from '@angular/core';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// ---- [ Component ] -----------------------------------------------
import { LoginComponent }  from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { LogoutComponent } from './logout/logout.component';

// ---- [ Cookie ] -----------------------------------------------
import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,
    LogoutComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'My-Xsrf-Cookie'
    }),
  ],
  exports: [
    LoginComponent,
    SignupComponent,
    LogoutComponent
  ],
  providers: [
    CookieService
  ],
  bootstrap: []
})
export class AuthModule { }

