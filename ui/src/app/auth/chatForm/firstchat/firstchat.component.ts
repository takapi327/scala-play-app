import { Component, OnInit }                  from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector:    'app-firstchat',
  templateUrl: './firstchat.component.html',
  styleUrls:   ['./firstchat.component.scss']
})
export class FirstchatComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {}

  firstName = new FormControl('', [
    Validators.required
  ]);

  lastName = new FormControl('', [
    Validators.required
  ]);

  phoneticFirst = new FormControl('', [
    Validators.required,
    Validators.pattern('/^[ア-ン゛゜ァ-ォャ-ョー「」、]+$/')
  ]);

  phoneticLast = new FormControl('', [
    Validators.required,
    Validators.pattern('/^[ア-ン゛゜ァ-ォャ-ョー「」、]+$/')
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
}
