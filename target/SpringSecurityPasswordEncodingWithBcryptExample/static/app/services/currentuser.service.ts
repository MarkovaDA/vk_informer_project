import {Injectable} from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {User} from '../models/user.model';

@Injectable()
export class CurrentUserService{
    
    private userUrl: string = 'http://localhost:8084/care_your_pet/client/get_currentuser';
    
    constructor(private http:Http){}
    
    getCurrentUser(): Promise<User> {
        return this.http.get(this.userUrl)
               .toPromise()
               .then(response => response.json() as User)
               .catch(this.handleError);
    }
    private handleError (error: Response) {
        console.error(error);
    } 
}


