import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class checkBoxOptions {
  checkBox = [
    { number: 1, value: "test1"},
    { number: 2, value: "test2"},
    { number: 3, value: "test3"},
    { number: 4, value: "test4"},
    { number: 5, value: "test5"}
  ];
}
