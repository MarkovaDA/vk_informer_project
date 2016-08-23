import {Injectable} from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Pet} from '../models/pet.model';

@Injectable()
export class PetService{
    private petsUrl: string = 'http://localhost:8084/care_your_pet/client/get_pets';
    
    constructor(private http:Http){}
    
    getPets(): Promise<Pet[]> {
        return this.http.get(this.petsUrl)
               .toPromise()
               .then(response => response.json() as Pet[])
               .catch(this.handleError);
    }
    private handleError (error: Response) {
        console.error(error);
    } 
}


