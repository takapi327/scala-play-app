// ---- [ Module ] -----------------------------------------------
import { CommonModule }                     from '@angular/common';
import { NgModule }                         from '@angular/core';
import { HttpClientModule }                 from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// ---- [ Component ] --------------------------------------
import { ElementComponent }     from './element';
import { LoginComponent }       from './authenticate/login/login.component';
import { SignupComponent }      from './authenticate/signup/signup.component';
import { LogoutComponent }      from './authenticate/logout/logout.component';
import { RadiobuttonComponent } from '../components/form/radiobutton/radiobutton.component';
import { RadioComponent }       from './location/radio/radio.component';

const COMPONENTS = [
  ElementComponent,
  LoginComponent,
  SignupComponent,
  LogoutComponent,
  RadiobuttonComponent,
  RadioComponent
]

const MODULES = [
  CommonModule,
  FormsModule,
  HttpClientModule,
  ReactiveFormsModule,
]

@NgModule({
  declarations: COMPONENTS,
  imports:      MODULES,
  exports:      COMPONENTS
})

export class ElementModule { }
