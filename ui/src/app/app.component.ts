import { Component }    from '@angular/core';

import { area }         from './interface/area';
import { prefectures }  from './interface/prefecture';
import { municipality } from './interface/municipality';

@Component({
  selector:    'app-root',
  templateUrl: './app.component.html',
  styleUrls:   ['./app.component.scss']
})
export class AppComponent {

  constructor(
    public areaOptions:         area,
    public prefectureOptions:   prefectures,
    public municipalityOptions: municipality
  ) {}

}
