import { Component } from '@angular/core';

import { area } from './interface/area'

@Component({
  selector:    'app-root',
  templateUrl: './app.component.html',
  styleUrls:   ['./app.component.scss']
})
export class AppComponent {

  constructor(
    public checkBoxOptions: area
  ) {}

}
