import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector:    'app-radiobutton',
  templateUrl: './radiobutton.component.html',
  styleUrls:   [
    './radiobutton.component.scss',
    '../../molecules/radio/radio.component.scss'
  ],
})
export class RadiobuttonComponent {

  select: string;

  @Input() inputName:    string;
  @Input() classs:       string[];
  @Input() valueOptions: {value: string, url: string};
  @Input() index:        number;

  @Output() eventValue = new EventEmitter();

  constructor() {}

  showValue(index: number, value: string) {
    this.eventValue.emit({index, value});
  }
}
