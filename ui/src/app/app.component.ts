import { Component } from '@angular/core';

import { checkBoxOptions } from './interface/checkOptions'

@Component({
  selector:    'app-root',
  templateUrl: './app.component.html',
  styleUrls:   ['./app.component.scss']
})
export class AppComponent {

  constructor(
    public checkBoxOptions: checkBoxOptions
  ) {}

}
