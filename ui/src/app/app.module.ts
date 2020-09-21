// ---- [ Module ] -----------------------------------------
import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
//import { AuthModule }       from './auth/auth.module'
//import { AppRoutingModule } from './app-routing.module';
import { AtomicModule }     from './atomic-design/atomic.module'

// ---- [ Component ] --------------------------------------
import { AppComponent }     from './app.component';

const MODULES = [
  BrowserModule,
  //AuthModule,
  AtomicModule,
  //AppRoutingModule
]

@NgModule({
  declarations: [AppComponent],
  imports:       MODULES,
  providers:    [],
  bootstrap:    [AppComponent]
})
export class AppModule { }
