import { Component } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { area }         from '../../../interface/area';
import { prefectures }  from '../../../interface/prefecture';
import { municipality } from '../../../interface/municipality';

@Component({
  selector:    'app-radio',
  templateUrl: './radio.component.html',
  styleUrls:   ['./radio.component.scss'],
  animations:  [
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
export class RadioComponent {
  
  constructor(
    public areaOptions:         area,
    public prefectureOptions:   prefectures,
    public municipalityOptions: municipality
  ) {}

  /**
    * ===== Variable =====
    */
   isOpenPref:  boolean;
   isOpenCity:  boolean;
   url:         string;
   prefecture:  {key: string[]};

   /**
     * ===== ReactiveForm Variable =====
     */
    area = new FormControl('', [
      Validators.required
    ])

    pref = new FormControl('', [
      Validators.required
    ])

    city = new FormControl('', [
      Validators.required
    ])

    locationSearchForm = new FormGroup({
      area: this.area,
      pref: this.pref,
      city: this.city
    })

  /**
    * ===== Method =====
    */
   // TODO: 以下命名に反して処理をさせ過ぎているから綺麗にしたい
   selectedArea(location: {index: number, value: string}): void {
   //  this.prefOptions = location.rows
     this.prefecture = this.prefectureOptions.prefecture[location.index][location.value]
     this.isOpenPref  = true
     console.log(location)
   }

   // TODO: 以下命名に反して処理をさせ過ぎているから綺麗にしたい
   selectedPref(location: {index: number, url: string}): void {
     console.log(location)
     /*
     this.municipalitySearchService.searchCityByPrefId(location.value)
       .subscribe(res => {
         res.unshift({label: '(都道府県名)すべて', urn: 'all'})
         this.cityOptions = res
       })
     */
     this.isOpenCity = true
   }

   selectedCity(paylod: {selected: string, urn: string}): void {
   }

   isShow() {
     this.isOpenPref = false
   }

  /**
    * ===== Search Action Method =====
    */
   searchAction(location: {index: number, url: string}): void {
     console.log(location)
     /*
     window.location.href = this.municipalitySearchService.ACTION_URL_LOCATION({prefUrl: value.pref, cityUrl: value.city})
     */
   }

   onSubmit(): void {
     this.searchAction(this.locationSearchForm.value)
   }

}
