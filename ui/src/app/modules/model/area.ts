import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class area {
  area = [
    { value: "関東",         url: "kanto"},
    { value: "近畿",         url: "kinki"},
    { value: "東北・北海道", url: "touhoku"},
    { value: "中部",         url: "chubu"},
    { value: "四国・中国",   url: "sikoku"},
    { value: "九州・沖縄",   url: "kyushu"}
  ];
}
