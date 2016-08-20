import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `<h1>Welcome to our clinic</h1>\n\
    <br>\n\
    <a href="entrance">Войти</a>
    <router-outlet></router-outlet>   `
})

export class AppComponent {}



