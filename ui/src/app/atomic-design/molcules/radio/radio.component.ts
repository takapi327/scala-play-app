import { Component, Input } from '@angular/core';

@Component({
  selector:    'app-radio',
  templateUrl: './radio.component.html',
  styleUrls:   ['./radio.component.scss']
})
export class RadioComponent {

  selectedArea: string;
  selectedPref: string;
  selectedCity: string;
  prefecture:   object[];
  municipality: object[];

  @Input() areaOptions:         object[];
  @Input() prefectureOptions:   {key: string[]};
  @Input() municipalityOptions: {key: string[]};

  constructor() {}

  showPref(event: {index: number, value: string}) {
    this.selectedArea = event.value
    this.prefecture = this.prefectureOptions[event.index][event.value]
  }

  showCity(event: {index: number, value: string}) {
    this.selectedPref = event.value
    this.municipality = this.municipalityOptions[event.index][event.value]
  }

  test(event: {index: number, value: string}) {
    this.selectedCity = event.value
    console.log(event.index)
    console.log(event.value)
    console.log(this.selectedArea + "/" + this.selectedPref + "/" + this.selectedCity)
  }

}
