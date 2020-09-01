import { Component, OnInit } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';

import { ChatformService } from '../../../service/chatform.service'

@Component({
  selector:    'app-secondchat',
  templateUrl: './secondchat.component.html',
  styleUrls:   ['./secondchat.component.scss'],
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
export class SecondchatComponent implements OnInit {

  constructor(
    private chatFormService: ChatformService
  ) { }

  ngOnInit(): void {
  }

  get showForm(){
    return this.chatFormService.isSecondChat
  }

  prefecturesInput(e: string){
    console.log(e)
  }
  cityInput(e: string){
    console.log(e)
  }
  townInput(e: string){
    localStorage.setItem('town', e)
  }
  extendedInput(e: string){
    console.log(e)
  }

  onSubmit() {
    this.chatFormService.isTherdChat = true;
  }
}
