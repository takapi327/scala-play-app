import { Component, OnInit } from '@angular/core';
import { CookieService }     from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector:    'app-logout',
  templateUrl: './logout.component.html',
  styleUrls:   ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  private isCookie: boolean;
  private cookieValue: string;

  constructor(
    private cookieService: CookieService,
    private router: Router
  ) {
    console.log(this.isCookie)
    console.log(!this.isCookie)    
    console.log(this.cookieValue)
    console.log(this.cookieService.check('My-Xsrf-Cookie'))
    console.log(this.cookieService.get("My-Xsrf-Cookie"))
  }

  ngOnInit(): void {
    this.cookieValue = this.cookieService.get("My-Xsrf-Cookie");
    this.isCookie = this.cookieService.check("My-Xsrf-Cookie");
    if(this.isCookie){
      this.router.navigate([ '/' ]);
    }
  }

}
