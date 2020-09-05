// ---- [ Module ] -----------------------------------------
import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { AuthModule }       from './auth/auth.module'
import { AppRoutingModule } from './app-routing.module';
import { AtomicModule }     from './atomic-design/atomic.module'

// ---- [ Component ] --------------------------------------
import { AppComponent }     from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AuthModule,
    AtomicModule,
    AppRoutingModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
