import {
  ControlContainer, ControlValueAccessor,
  FormControl,      FormControlDirective
} from '@angular/forms';

import { Injector, Input, ViewChild, Directive } from '@angular/core';

@Directive()
export class ControlValueAccessorConnector implements ControlValueAccessor {

  @ViewChild(
    FormControlDirective,
    { static: true }
  )

  /**
   * Variable
   */
  formControlDirective: FormControlDirective;

  /**
   * Input Variable
   */
  @Input() formControl:     FormControl;
  @Input() formControlName: string;

  get control() {
    return this.formControl || this.controlContainer.control.get(this.formControlName);
  }

  /**
   * constructorによるDI
   */
  constructor(
    private injector: Injector
  ) {}

  get controlContainer() {
    return this.injector.get(ControlContainer);
  }

  /**
   * ControlValueAccessor Methods
   *
   * @see <a href="https://github.com/angular/angular/blob/10.0.8/packages/forms/src/directives/control_value_accessor.ts#L10-L132">control_value_accessor.ts</a>
   *
   * HACK: this.formControlDirective is may be underfind
   */
  writeValue(obj: any): void {
    if(this.formControlDirective){
      this.formControlDirective.valueAccessor.writeValue(obj);
    }
  }

  registerOnChange(fn: any): void {
    if(this.formControlDirective){
      this.formControlDirective.valueAccessor.registerOnChange(fn);
    }
  }

  registerOnTouched(fn: any): void {
    if(this.formControlDirective){
      this.formControlDirective.valueAccessor.registerOnTouched(fn);
    }
  }

  setDisabledState(isDisabled: boolean): void {
    this.formControlDirective.valueAccessor.setDisabledState(isDisabled);
  }
}
