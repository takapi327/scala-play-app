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

  fulls: number[] = [0]
}
