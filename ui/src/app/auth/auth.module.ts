// ---- [ Module ] -----------------------------------------------
import { CommonModule }     from '@angular/common';
import { NgModule }         from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// ---- [ Component ] -----------------------------------------------
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  exports: [
    LoginComponent
  ],
  providers: [
  ],
  bootstrap: []
})
export class AuthModule { }

