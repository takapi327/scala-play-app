import { Component, Input } from '@angular/core';

@Component({
  selector:    'app-radio',
  templateUrl: './radio.component.html',
  styleUrls:   ['./radio.component.scss']
})
export class RadioComponent {

  selectedArea:     string;
  selectedPref:     string;
  selectedCity:     string;
  prefecture:       object[];
  municipality:     object[];

  @Input() fulls:               number[];
  @Input() areaOptions:         object[];
  @Input() prefectureOptions:   {key: string[]};
  @Input() municipalityOptions: {key: string[]};

  constructor() {}

  showPref(i: number, area: string) {
    this.prefecture = this.prefectureOptions[i][area]
  }

  showCity(i: number, city: string) {
    this.municipality = this.municipalityOptions[i][city]
  }

}
