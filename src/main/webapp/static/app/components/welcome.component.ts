import { Component } from '@angular/core';
import {HttpPetService} from '../services/http.pet.service';

@Component({
  selector: 'pets',
  //template: `<h1>hello</h1>`
  templateUrl: 'app/templates/pets.template.html',
  providers: [HttpPetService]
})



export class AppComponent {
    pets;     

   
    constructor(private httpPetService: HttpPetService){
    
    }
    
    getPetsFromServer(){
        
        this.httpPetService.getPets()
            .subscribe(
                data => this.pets 
                        //= JSON.stringify(data), // put the data returned from the server in our variable
                        = data,                                
                error => console.log("Error HTTP GET Service"), // in case of failure show this message
                () => {console.log(this.pets[0])}
            );
    }
}





