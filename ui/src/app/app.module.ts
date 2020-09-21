// ---- [ Module ] -----------------------------------------
import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { ElementModule }    from './elements/element.module';

// ---- [ Component ] --------------------------------------
import { AppComponent }     from './app.component';

const MODULES = [
  BrowserModule,
  ElementModule
]

@NgModule({
  declarations: [AppComponent],
  imports:       MODULES,
  providers:    [],
  bootstrap:    [AppComponent]
})
export class AppModule { }
