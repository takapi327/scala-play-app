import { Component}            from '@angular/core';
import { NG_VALUE_ACCESSOR }   from '@angular/forms'

import { Location, LocationArea } from '@modules/model/location';
/* this project import */
import { nnector } from '@modules/control-value-accessor-connector';

@Component({
  selector:    'app-area',
  templateUrl: './area.component.html',
  styleUrls:   ['./area.component.scss']
})
export class AreaComponent extends ControlValueAccessorConnector {

  constructor(
    public areaOptions: LocationArea[] = Location.AREA_WITH_PREF,
    injector: Injector
  ) {
    super(injector);
  }

  /**
   * ===== Method =====
   */
  onClick() {
    const value = this.control.value
    this.selectLocation.emit(value);
  }
}
