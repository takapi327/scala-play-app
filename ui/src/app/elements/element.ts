import { Component, Injector } from '@angular/core';
import { createCustomElement } from '@angular/elements';
import { LoginComponent }      from '@elements/authenticate/login/login.component';

@Component({
  selector: 'ng-element-bootstrap',
  template: '<ng-content></ng-content>'
})

export class ElementComponent {

  constructor(
    private injector: Injector
  ) {
    customElements.define('login-form', createCustomElement(LoginComponent, {injector: this.injector}));
  }

}
