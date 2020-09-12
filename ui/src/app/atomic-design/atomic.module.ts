import { NgModule }          from '@angular/core';
import { CommonModule }      from '@angular/common';

import { CheckboxComponent } from './atomos/checkbox/checkbox.component'
import { RadioComponent }    from './atomos/radio/radio.component'

@NgModule({
  declarations: [
    CheckboxComponent,
    RadioComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CheckboxComponent,
    RadioComponent
  ]
})
export class AtomicModule { }
