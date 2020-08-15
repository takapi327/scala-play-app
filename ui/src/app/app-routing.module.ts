import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './guard/auth.guard'

import { LoginComponent }  from './auth/login/login.component'
import { SignupComponent } from './auth/signup/signup.component';
import { LogoutComponent } from './auth/logout/logout.component';

const routes: Routes = [
  { path: 'api/auth/loginForm',  component: LoginComponent  },
  { path: 'api/auth/signupForm', component: SignupComponent },
  { path: 'api/auth/logoutForm', component: LogoutComponent, canActivate: [AuthGuard] },
  { path: '**',                  redirectTo: '/'             }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
