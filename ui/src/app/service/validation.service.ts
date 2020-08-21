import { Injectable }              from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor() { }

  email = new FormControl('', [
    Validators.required,
    Validators.email
  ]);
  
  isEmailValidation(): FormControl {
    return this.email
  }
  
}
