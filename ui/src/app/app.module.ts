import { BrowserModule } from '@angular/platform-browser';
import { NgModule }      from '@angular/core';
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent }     from './app.component';

import { HttpClientModule }    from '@angular/common/http';
import { HttpClientComponent } from './http-client/http-client.component';
import { HttpClientService }   from './service/http-client.service';

@NgModule({
  declarations: [
    AppComponent,
    HttpClientComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    HttpClientService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
