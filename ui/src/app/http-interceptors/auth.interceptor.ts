import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(
    request: HttpRequest<unknown>,
    next:    HttpHandler
  ): Observable<HttpEvent<unknown>> {
    console.log(request.headers.get("My-Xsrf-Cookie"))
    return next.handle(request);
  }
}
