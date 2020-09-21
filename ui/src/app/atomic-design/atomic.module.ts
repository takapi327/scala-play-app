import { NgModule }             from '@angular/core';
import { CommonModule }         from '@angular/common';
import { FormsModule, ReactiveFormsModule }          from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { CheckboxComponent }    from './molecules/checkbox/checkbox.component';
import { RadioComponent }       from './molecules/radio/radio.component';
import { RadiobuttonComponent } from './atomos/radiobutton/radiobutton.component';
import { RadiolistComponent }   from './organisms/radiolist/radiolist.component';

const COMPONENTS = [
  CheckboxComponent,
  RadioComponent,
  RadiobuttonComponent,
  RadiolistComponent
]

const MODULES = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule,
  BrowserAnimationsModule
]

@NgModule({
  declarations: COMPONENTS,
  imports:      MODULES,
  exports:      COMPONENTS
})
export class AtomicModule { }
