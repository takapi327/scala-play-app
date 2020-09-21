// ---- [ Module ] -----------------------------------------------
import { CommonModule }     from '@angular/common';
import { NgModule }         from '@angular/core';

// ---- [ Component ] --------------------------------------
import { ElementComponent } from './element';

const COMPONENTS = [
  ElementComponent
]

const MODULES = [
  CommonModule
]

@NgModule({
  declarations: COMPONENTS,
  imports:      MODULES,
  exports:      COMPONENTS
})

export class ElementModule { }
