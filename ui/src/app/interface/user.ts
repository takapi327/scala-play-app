export interface User {
  email:    string;
  password: string;
}

export interface Signup {
  firstName: string;
  lastName:  string;
  email:     string;
  password:  string;
}

export interface Email {
  email: string;
}

export class Auth {
  constructor(
    public isAuth: boolean
  ){}
}
