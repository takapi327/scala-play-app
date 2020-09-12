import { Component }   from '@angular/core';

import { area }        from './interface/area';
import { prefectures } from './interface/prefecture';

@Component({
  selector:    'app-root',
  templateUrl: './app.component.html',
  styleUrls:   ['./app.component.scss']
})
export class AppComponent {

  constructor(
    public area:        area,
    public prefectures: prefectures
  ) {}

}
