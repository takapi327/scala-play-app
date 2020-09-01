import { Component, OnInit } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';

import { ChatformService } from '../../../service/chatform.service'

@Component({
  selector:    'app-thirdchat',
  templateUrl: './thirdchat.component.html',
  styleUrls:   ['./thirdchat.component.scss'],
  animations:  [
     trigger('openClose', [
      state('open', style({
        height:          '400px',
        opacity:         1,
        backgroundColor: 'yellow'
      })),
      state('closed', style({
        opacity: 0
      })),
      transition('closed => open', [
        animate('0.2s')
      ]),
    ]),
  ]
})
export class ThirdchatComponent implements OnInit {

  constructor(
    private chatFormService: ChatformService
  ) { }

  ngOnInit(): void {
  }

  get showForm(){
    return this.chatFormService.isThirdChat
  }

}
