// ---- [ Module ] -----------------------------------------------
import { CommonModule }     from '@angular/common';
import { NgModule }         from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// ---- [ Component ] -----------------------------------------------
import { LoginComponent }  from './login/login.component';
import { SignupComponent } from './signup/signup.component';

@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  exports: [
    LoginComponent,
    SignupComponent
  ],
  providers: [],
  bootstrap: []
})
export class AuthModule { }

