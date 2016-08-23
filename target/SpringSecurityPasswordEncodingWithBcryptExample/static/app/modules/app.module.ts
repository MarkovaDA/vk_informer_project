import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { AppComponent }  from '../components/welcome.component';
import {PetService} from '../services/pet.service';
import {CurrentUserService} from '../services/currentuser.service';
import {HttpModule} from '@angular/http';


@NgModule({
  imports:      [ BrowserModule, HttpModule, FormsModule],
  declarations: [ AppComponent],
  bootstrap:    [ AppComponent],
  providers: [PetService, CurrentUserService]
})
export class AppModule {}


