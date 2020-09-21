import { Component }                                  from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { FormControl, Validators, FormBuilder }       from '@angular/forms';

import { area }         from '@modules/model/area';
import { prefectures }  from '@modules/model/prefecture';
import { municipality } from '@modules/model/municipality';

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
    public  areaOptions:         area,
    public  prefectureOptions:   prefectures,
    public  municipalityOptions: municipality,
    private formBuilder:         FormBuilder
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
    areaControl = new FormControl('', [
      Validators.required
    ])

    prefControl = new FormControl('', [
      Validators.required
    ])

    locationSearchForm = this.formBuilder.group({
      areaControl: this.areaControl,
      prefControl: this.prefControl
    })

  /**
    * ===== Method =====
    */
   // TODO: 以下命名に反して処理をさせ過ぎているから綺麗にしたい
   selectedArea(location: {index: number, value: string}): void {
     this.prefecture = this.prefectureOptions.prefecture[location.index][location.value]
     this.isOpenPref = true
   }

   // TODO: 以下命名に反して処理をさせ過ぎているから綺麗にしたい
   selectedPref(location: {index: number, url: string}): void {
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
   }

   onSubmit(): void {
     this.searchAction(this.locationSearchForm.value)
   }

}
