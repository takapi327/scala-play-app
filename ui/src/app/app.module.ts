// ---- [ Module ] -----------------------------------------
import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { AuthModule }       from './auth/auth.module'
import { AppRoutingModule } from './app-routing.module';

// ---- [ Component ] --------------------------------------
import { AppComponent }     from './app.component';
import { LogoutComponent } from './logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    LogoutComponent
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
