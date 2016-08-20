import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ClientComponent } from '../components/client.component';

@NgModule({
  imports:      [ BrowserModule ],
  declarations: [ ClientComponent ],
  bootstrap:    [ ClientComponent ]
})
export class ClientModule {}


