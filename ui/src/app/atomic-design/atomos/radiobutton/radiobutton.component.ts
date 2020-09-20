/* @angular import */
import { Component, Input, Output, EventEmitter, Injector } from '@angular/core';
import { NG_VALUE_ACCESSOR }                                from '@angular/forms'

/* this project import */
import { ControlValueAccessorConnector } from '../../control-value-accessor-connector';

@Component({
  selector:    'app-radiobutton',
  templateUrl: './radiobutton.component.html',
  styleUrls:   ['./radiobutton.component.scss'],
  providers:   [
    {
      provide:     NG_VALUE_ACCESSOR,
      useExisting: RadiobuttonComponent,
      multi:       true
    }
  /*,
  {
    provide: NgControl,
    useExisting: forwardRef(() => ControlValueAccessorConnector)
  }
  */
  ]
})
export class RadiobuttonComponent extends ControlValueAccessorConnector {

  @Input() radioName:    string;
  @Input() radioOptions: {value: string, url: string};

  @Output() selectLocation = new EventEmitter();

  //selected:   string;
  //disabled: boolean;
  //@ViewChild('input') private inputElementRef: ElementRef;

  constructor(
    injector: Injector
    //@Self() public controlDir: NgControl
  ) {
    super(injector);
    console.log(this.formControlDirective)
    //controlDir.valueAccessor = this;
  }
/*
  ngOnInit(){
    this.radioValue = this.radioOptions
    console.log(this.radioOptions)
    const control = this.controlDir.control;
    control.setValidators(this.validate);
    control.updateValueAndValidity();
  }
    */

  /*
  validate(ctrl: AbstractControl){
    return Validators.required(ctrl);
  }
  */
  /**
   * ===== Method =====
   */
  onChange(index: number) {
    const value = this.control.value
    this.selectLocation.emit({index, value});
  }

  //onTouched: (obj: any) => void;
  /**
   * ===== ControlValueAccessor =====
   */
  /*
  writeValue(obj: any): void {
    this.inputElementRef = obj;
  }

  registerOnChange(fn: any): void {
    this.onChange
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled
  }
  */
}
