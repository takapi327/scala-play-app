import { Component, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { NG_VALUE_ACCESSOR, ControlValueAccessor}                        from '@angular/forms'

@Component({
  selector:    'app-radiobutton',
  templateUrl: './radiobutton.component.html',
  styleUrls:   ['./radiobutton.component.scss'],
  providers:   [
    {
       provide:     NG_VALUE_ACCESSOR,
       useExisting: RadiobuttonComponent,
       multi:       true,
     },
   ]
})
export class RadiobuttonComponent implements ControlValueAccessor {

  select: string;

  @Input() radioName:    string;
  @Input() radioOptions: {value: string, url: string};

  @Output() selectLocation = new EventEmitter();

  selected: string;
  disabled: boolean;
  @ViewChild('input') private input: ElementRef;

  constructor() {}

  /**
   * ===== Method =====
   */
  onChange(index: number, value: string) {
    this.selectLocation.emit({index, value});
  }

  onTouched: (obj: any) => void;
  /**
   * ===== ControlValueAccessor =====
   */
  writeValue(obj: any): void {
    this.input.nativeElement.value = obj;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled
  }
}
