import { Component, OnInit }                  from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';

import { ValidationMessages }   from '../../validation-messages';

@Component({
  selector:    'app-firstchat',
  templateUrl: './firstchat.component.html',
  styleUrls:   ['./firstchat.component.scss']
})
export class FirstchatComponent implements OnInit {

  constructor(
    public validationMessages: ValidationMessages
  ) {}

  ngOnInit(): void {}

  firstName = new FormControl('', [
    Validators.required
  ]);

  lastName = new FormControl('', [
    Validators.required
  ]);

  phoneticFirst = new FormControl('', [
    Validators.required,
    Validators.pattern('^[ア-ン゛゜ァ-ォャ-ョー「」、]+$')
  ]);

  phoneticLast = new FormControl('', [
    Validators.required,
    Validators.pattern('^[ア-ン゛゜ァ-ォャ-ョー「」、]+$')
  ]);

  birthday = new FormControl('',[
    Validators.required
  ]);

  firstChatForm = new FormGroup({
    firstName:     this.firstName,
    lastName:      this.lastName,
    phoneticFirst: this.phoneticFirst,
    phoneticLast:  this.phoneticLast,
    birthday:      this.birthday
  })

  onSubmit() {
    console.log(this.firstName.value)
    console.log(this.lastName.value)
    console.log(this.phoneticFirst.value)
    console.log(this.phoneticLast.value)
    console.log(this.birthday.value)
  }
}
