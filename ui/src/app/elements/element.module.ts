// ---- [ Module ] -----------------------------------------------
import { CommonModule }                     from '@angular/common';
import { NgModule }                         from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// ---- [ Component ] --------------------------------------
import { ElementComponent } from './element';
import { LoginComponent }   from './authenticate/login/login.component';
import { SignupComponent }  from './authenticate/signup/signup.component';
import { LogoutComponent }  from './authenticate/logout/logout.component';

const COMPONENTS = [
  ElementComponent,
  LoginComponent,
  SignupComponent,
  LogoutComponent
]

const MODULES = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule,
]

@NgModule({
  declarations: COMPONENTS,
  imports:      MODULES,
  exports:      COMPONENTS
})

export class ElementModule { }
