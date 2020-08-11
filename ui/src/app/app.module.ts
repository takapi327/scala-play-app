// ---- [ Module ] -----------------------------------------
import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { AuthModule }       from './auth/auth.module'
import { AppRoutingModule } from './app-routing.module';

// ---- [ Component ] --------------------------------------
import { AppComponent }     from './app.component';
import { SignupComponent } from './signup/signup.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    AuthModule,
    AppRoutingModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
