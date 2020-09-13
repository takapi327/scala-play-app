import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector:    'app-radio',
  templateUrl: './radio.component.html',
  styleUrls:   ['./radio.component.scss']
})
export class RadioComponent {
  
  @Input() valueOptions: {key: string[]};

  @Output() importValue = new EventEmitter();

  constructor() {}

  exportValue(event: {index: number, value: string}) {
    this.importValue.emit(event);
  }
}
