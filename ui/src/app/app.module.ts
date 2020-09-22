// ---- [ Module ] -----------------------------------------
import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { ElementModule }    from './elements/element.module';

// ---- [ Component ] --------------------------------------
import { AppComponent }     from './app.component';
import { ElementComponent } from '@elements/element';

const MODULES = [
  BrowserModule,
  ElementModule
]

@NgModule({
  declarations:    [AppComponent],
  imports:         MODULES,
  providers:       [],
  bootstrap:       [ElementComponent],
  entryComponents: [ElementComponent]
})
export class AppModule { }
