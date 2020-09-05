import { Component, OnInit } from '@angular/core';

import { checkBoxOptions } from '../../../interface/checkOptions'

@Component({
  selector:    'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls:   ['./checkbox.component.scss']
})
export class CheckboxComponent implements OnInit {

  constructor(
    public checkBoxOptions: checkBoxOptions
  ) { }

  ngOnInit(): void {
  }

  fulls: number[] = []

  /**
   * TODO: eventを全て取得しているのを修正したい
   * inputのcheckeedイベントのbooleanで処理わけする方が本当はいい
   */
  click(event: any){
    if(event.target.classList.value.includes('selected')){
      event.target.classList.remove('selected')
    } else {
      event.target.classList.add('selected')
    }
  }
}
