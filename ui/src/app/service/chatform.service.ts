import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChatformService {

  constructor() { }

  isSecondChat: boolean = false;
  isTherdChat:  boolean = false;
}
