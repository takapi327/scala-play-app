import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from '../app/auth/login/login.component'
import { SignupComponent } from './auth/signup/signup.component';

const routes: Routes = [
  { path: 'api/auth/loginForm',  component: LoginComponent },
  { path: 'api/auth/signupForm', component: SignupComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
