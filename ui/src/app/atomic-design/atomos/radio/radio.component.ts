import { Component, Input } from '@angular/core';

@Component({
  selector:    'app-radio',
  templateUrl: './radio.component.html',
  styleUrls:   ['./radio.component.scss']
})
export class RadioComponent {

  selected:   string;
  prefecture: object[];

  @Input() fulls:       number[];
  @Input() area:        object[];
  @Input() prefectures: {key: string[]};

  constructor() {}

  show(i: number, area: string) {
    this.prefecture = this.prefectures[i][area]
  }

}
