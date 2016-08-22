import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { AppComponent }  from '../components/welcome.component';
import {PetService} from '../services/pet.service';
//import { HTTP_PROVIDERS } from '@angular/http';
import {HttpModule} from '@angular/http';


@NgModule({
  imports:      [ BrowserModule, HttpModule, FormsModule],
  declarations: [ AppComponent],
  bootstrap:    [ AppComponent],
  //providers:    [HTTP_PROVIDERS]
  providers: [PetService]
})
export class AppModule {}


