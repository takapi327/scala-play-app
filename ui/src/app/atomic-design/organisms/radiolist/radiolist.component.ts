import { Component } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

import { area }         from '../../../interface/area';
import { prefectures }  from '../../../interface/prefecture';
import { municipality } from '../../../interface/municipality';

@Component({
  selector:    'app-radiolist',
  templateUrl: './radiolist.component.html',
  styleUrls:   ['./radiolist.component.scss'],
  animations: [
    trigger('openClose', [
      state('open', style({
         opacity: 1,
      })),
      state('closed', style({
         display: 'none',
         opacity: 0,
      })),
      transition('open => closed', [
         animate('0.2s')
      ]),
      transition('closed => open', [
         animate('0.2s')
      ]),
      transition('* => void', [
         animate('0.2s')
      ]),
      transition('void => *', [
         animate('0.2s')
      ]),
    ])
  ]
})
export class RadiolistComponent {

  selectedArea: string;
  selectedPref: string;
  selectedCity: string;
  isOpenPref:   boolean;
  isOpenCity:   boolean;
  prefecture:   {key: string[]};
  municipality: {key: string[]};

  constructor(
    public areaOptions:         area,
    public prefectureOptions:   prefectures,
    public municipalityOptions: municipality
  ) {
    this.isOpenPref = false;
    this.isOpenCity = false;
  }

  showPref(event: {index: number, value: string}) {
    this.selectedArea = event.value
    this.prefecture = this.prefectureOptions.prefecture[event.index][event.value]
    this.isOpenPref = true;
  }

  showCity(event: {index: number, value: string}) {
    this.selectedPref = event.value
    this.municipality = this.municipalityOptions.municipality[event.index][event.value]
    this.isOpenCity = true;
  }

  closePref() {
    this.isOpenPref = false;
  }

  closeCity() {
    this.isOpenCity = false;
  }

  show(event: {index: number, value: string}) {
    this.selectedCity = event.value
    console.log(this.selectedArea + "/" + this.selectedPref + "/" + this.selectedCity)
  }
}
