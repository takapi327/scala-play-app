export interface LocationName {
  label:  string,
  value?: string,
  urn:    string
}

export interface LocationArea {
  label: string,
  urn:   string,
  rows:  LocationName[]
}

export interface LocationCity {
  nameOmitPref:  string,
  urn:           string
}

export interface OutputLocation {
  value: string,
  urn:   string,
  rows:  LocationName[]
}

export interface LocationList {
  areaUrnControl: string,
  prefUrnControl: string,
  cityUrnControl: string
}

export interface GetApiCity {
  nameOmitPref: string,
  urn:          string
}

export module Location {
  export const AREA_WITH_PREF = [
    {
      label: '関東', urn: 'kanto',
      rows: [
        { label: '東京都',   value: '13000', urn: 'tokyo' },
        { label: '神奈川県', value: '14000', urn: 'kanagawa' },
        { label: '埼玉県',   value: '11000', urn: 'saitama' },
        { label: '千葉県',   value: '12000', urn: 'chiba' },
        { label: '茨城県',   value: '08000', urn: 'ibaraki' },
        { label: '栃木県',   value: '09000', urn: 'tochigi' },
        { label: '群馬県',   value: '10000', urn: 'gunnma' },
      ]
    }, {
      label: '近畿', urn: 'kinki',
      rows: [
        { label: '滋賀県',   value: '25000', urn: 'shiga' },
        { label: '京都府',   value: '26000', urn: 'kyouto' },
        { label: '大阪府',   value: '27000', urn: 'osaka' },
        { label: '兵庫県',   value: '28000', urn: 'hyougo' },
        { label: '奈良県',   value: '29000', urn: 'nara' },
        { label: '和歌山県', value: '30000', urn: 'wakayama' },
      ]
    }, {
      label: '東海', urn: 'toukai',
      rows: [
        { label: '岐阜県',   value: '21000', urn: 'gifu' },
        { label: '静岡県',   value: '22000', urn: 'shizuoka' },
        { label: '愛知県',   value: '23000', urn: 'aichi' },
        { label: '三重県',   value: '24000', urn: 'mie' },
      ]
    }, {
      label: '北海道・東北', urn: 'hokkaidou/touhoku',
      rows: [
        { label: '北海道',   value: '01000', urn: 'hokkaido' },
        { label: '青森県',   value: '02000', urn: 'aomori' },
        { label: '岩手県',   value: '03000', urn: 'iwate' },
        { label: '宮城県',   value: '04000', urn: 'miyagi' },
        { label: '秋田県',   value: '05000', urn: 'akita' },
        { label: '山形県',   value: '06000', urn: 'yamagata' },
        { label: '福島県',   value: '07000', urn: 'hukushima' },
      ]
    }, {
      label: '北陸・北信越', urn: 'hokuriku/hokushinti',
      rows: [
        { label: '新潟県',   value: '15000', urn: 'niigata' },
        { label: '富山県',   value: '16000', urn: 'toyama' },
        { label: '石川県',   value: '17000', urn: 'ishikawa' },
        { label: '福井県',   value: '18000', urn: 'fukui' },
        { label: '山梨県',   value: '19000', urn: 'yamanashi' },
        { label: '長野県',   value: '20000', urn: 'nagano' },
      ]
    }, {
      label: '中国・四国', urn: 'chuugoku/sikoku',
      rows: [
        { label: '鳥取県',   value: '31000', urn: 'tottori' },
        { label: '島根県',   value: '32000', urn: 'shimane' },
        { label: '岡山県',   value: '33000', urn: 'okayama' },
        { label: '広島県',   value: '34000', urn: 'hiroshima' },
        { label: '山口県',   value: '35000', urn: 'yamaguchi' },
        { label: '徳島県',   value: '36000', urn: 'tokushima' },
        { label: '香川県',   value: '37000', urn: 'kagawa' },
        { label: '愛媛県',   value: '38000', urn: 'ehime' },
        { label: '高知県',   value: '39000', urn: 'kouchi' },
      ]
    }, {
      label: '九州・沖縄', urn: 'kyuushuu/okinawa',
      rows: [
        { label: '福岡県',   value: '40000', urn: 'fukuoka' },
        { label: '佐賀県',   value: '41000', urn: 'saga' },
        { label: '長崎県',   value: '42000', urn: 'nagano' },
        { label: '熊本県',   value: '43000', urn: 'kumamoto' },
        { label: '大分県',   value: '44000', urn: 'oita' },
        { label: '宮崎県',   value: '45000', urn: 'miyagi' },
        { label: '鹿児島県', value: '46000', urn: 'kagoshima' },
        { label: '沖縄県',   value: '47000', urn: 'okinawa' }
      ]
    }
  ]  
}
