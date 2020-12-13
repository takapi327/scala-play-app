import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class prefectures {

  prefecture = [
    { "kanto": [
      { value: "東京都",   url: "tokyo"},
      { value: "神奈川県", url: "kanagawa"},
      { value: "千葉県",   url: "ciba"},
      { value: "埼玉県",   url: "saitama"},
      { value: "群馬県",   url: "gunma"},
      { value: "栃木県",   url: "totigi"},
      { value: "茨城県",   url: "ibaragi"}      
    ]},
    { "kinki": [
      { value: "大阪府",   url: "oosaka"},
      { value: "兵庫県",   url: "hyougo"},
      { value: "京都府",   url: "kyouto"},
      { value: "奈良県",   url: "nara"},
      { value: "和歌山県", url: "wakayama"},
      { value: "滋賀県",   url: "siga"}
    ]},
    { "touhoku": [
      { value: "北海道",   url: "hokkaido"},
      { value: "青森県",   url: "aomori"},
      { value: "岩手県",   url: "iwate"},
      { value: "宮城県",   url: "miyagi"},
      { value: "秋田県",   url: "akita"},
      { value: "山形県",   url: "yamagata"},
      { value: "福島県",   url: "hukusima"}      
    ]},
    { "chubu": [
      { value: "新潟県",   url: "niigata"},
      { value: "富山県",   url: "toyama"},
      { value: "石川県",   url: "isikawa"},
      { value: "福井県",   url: "hukui"},
      { value: "山梨県",   url: "yamanasi"},
      { value: "長野県",   url: "nagano"},
      { value: "岐阜県",   url: "gihu"},
      { value: "静岡県",   url: "sizuoka"},
      { value: "愛知県",   url: "aiti"},
    ]},
    { "sikoku": [
      { value: "鳥取県",   url: "tottori"},
      { value: "島根県",   url: "simane"},
      { value: "岡山県",   url: "okayama"},
      { value: "広島県",   url: "hirosima"},
      { value: "山口県",   url: "yamaguti"},
      { value: "徳島県",   url: "tokusima"},
      { value: "香川県",   url: "kagawa"},
      { value: "愛媛県",   url: "ehime"},
      { value: "高知県",   url: "kouti"},
    ]},
    { "kyushu": [
      { value: "福岡県",   url: "hukuoka"},
      { value: "佐賀県",   url: "saga"},
      { value: "長崎県",   url: "nagasaki"},
      { value: "熊本県",   url: "kumamoto"},
      { value: "大分県",   url: "ooita"},
      { value: "宮崎県",   url: "miyazaki"},
      { value: "鹿児島県", url: "kagosima"},
      { value: "沖縄県",   url: "okinawa"}
    ]}
  ];
}
