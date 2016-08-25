import { Component, OnInit } from '@angular/core';
import {PetService} from '../services/pet.service';
import {CurrentUserService} from '../services/currentuser.service';
import {Pet} from '../models/pet.model';
import {User} from '../models/user.model';

@Component({
  selector: 'pets',
  templateUrl: 'app/templates/pets.template.html',
})



export class AppComponent implements OnInit{
    pets: Pet[]; 
    currentUser: User;  //текущий зарегистрированный пользователь
    current: User = new User();
    error: any;
   
    constructor(private petService:PetService, private currentUserService: CurrentUserService){}
    
    getPets(): void {
    this.petService
        .getPets()
        .then(pets => this.pets = pets)
        .catch(error => this.error = error);
    }
    }
}





    getCurrentUser():void{
        this.currentUserService
            .getCurrentUser()
            .then(currentUser => this.current = currentUser)
            .catch(error => this.error = error);
    }
    ngOnInit():void{
        this.getPets();
        this.getCurrentUser();
