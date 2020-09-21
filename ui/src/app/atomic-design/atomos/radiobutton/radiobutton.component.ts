/* @angular import */
import { Component, Input, Output, EventEmitter, Injector } from '@angular/core';
import { NG_VALUE_ACCESSOR }                                from '@angular/forms'

/* this project import */
import { ControlValueAccessorConnector } from '../../../modules/control-value-accessor-connector';

@Component({
  selector:    'app-radiobutton',
  templateUrl: './radiobutton.component.html',
  styleUrls:   ['./radiobutton.component.scss'],
  providers:   [{
    provide:     NG_VALUE_ACCESSOR,
    useExisting: RadiobuttonComponent,
    multi:       true
  }]
})
export class RadiobuttonComponent extends ControlValueAccessorConnector {

  @Input() radioName:    string;
  @Input() radioOptions: {value: string, url: string};

  @Output() selectLocation = new EventEmitter();

  constructor(
    injector: Injector
  ) {
    super(injector);
  }

  /**
   * ===== Method =====
   */
  onChange(index: number) {
    const value = this.control.value
    this.selectLocation.emit({index, value});
  }

}
