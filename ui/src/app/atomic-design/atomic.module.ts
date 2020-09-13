import { NgModule }             from '@angular/core';
import { CommonModule }         from '@angular/common';
import { FormsModule }          from '@angular/forms';

import { CheckboxComponent }    from './molcules/checkbox/checkbox.component';
import { RadioComponent }       from './molcules/radio/radio.component';
import { RadiobuttonComponent } from './atomos/radiobutton/radiobutton.component';

const COMPONENTS = [
  CheckboxComponent,
  RadioComponent,
  RadiobuttonComponent
]

const MODULES = [
  CommonModule,
  FormsModule
]

@NgModule({
  declarations: COMPONENTS,
  imports:      MODULES,
  exports:      COMPONENTS
})
export class AtomicModule { }
