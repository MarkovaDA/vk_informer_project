import { Component, OnInit } from '@angular/core';
import {PetService} from '../services/pet.service';
import {Pet} from '../models/pet.model';

@Component({
  selector: 'pets',
  //template: `<h1>hello</h1>`
  templateUrl: 'app/templates/pets.template.html',
})



export class AppComponent implements OnInit{
    pets: Pet[];     
    error: any;
   
    constructor(private petService: PetService){}
    
    getPets(): void {
    this.petService
        .getPets()
        .then(pets => this.pets = pets)
        .catch(error => this.error = error);
    }
    ngOnInit():void{
        this.getPets();
    }
}





