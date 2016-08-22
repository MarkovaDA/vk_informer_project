import {Injectable} from '@angular/core';
import {Http,Response} from '@angular/http';
import {Headers, RequestOptions} from '@angular/http';
import {Observable}     from 'rxjs/Observable';

@Injectable()
export class HttpPetService{
    private petsUrl: string = 'http://localhost:8084/care_your_pet/client/get_pets';
    
    constructor(private _http:Http){}
    
    getPets(){
        return this._http.get(this.petsUrl).map(res => res.json());
    }
    private handleError (error: Response) {
        console.error(error);
        return Observable.throw(error.json() || ' error');
    }
}


