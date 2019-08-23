import { Injectable } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, BehaviorSubject } from 'rxjs/index';
import { map, catchError } from 'rxjs/operators';
import { ApiResponse } from '../../app/models/api.response';
import { User } from '../../app/models/user';
import { StockmarketLoginComponent } from '../stockmarket-login/stockmarket-login.component';
import { environment } from '../../environments/environment';



// import { catchError } from 'rxjs/operators/catchError'; 



@Injectable({ providedIn: 'root' })

export class LoginHandlerService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  public error = false;
  public value = {};
  private API_URL = `${environment.apiUrl}`;



  constructor(
    private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(sessionStorage.getItem('currentUsername')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }


  public login(username: string) {
    const payload = {
      name: username,
      playerId: 0,
      gameLobbyId: 0
    }
    return this.http.post<any>(`${this.API_URL}/signup`, payload).pipe(map(user => {
     
        sessionStorage.setItem('currentUsername', JSON.stringify(user));
       
      return user;
    }));
  }



  logout() {

    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }


  // login(loginPayload): Observable<ApiResponse> {
  //   const auth: AuthClass = {
  //     Username: this.authClass.Username
  //   };
  //   return this.http.post<ApiResponse>('http://localhost:8080/signup',loginPayload).pipe(catchError(val => of(val)));
  // }

  // getUsers(): Observable<ApiResponse> {
  //   return this.http.get<ApiResponse>(this.baseUrl);
  // }

  // getUserById(id: number): Observable<ApiResponse> {
  //   return this.http.get<ApiResponse>(this.baseUrl + id);
  // }

  // createUser(user: User): Observable<ApiResponse> {
  //   return this.http.post<ApiResponse>(this.baseUrl, user);
  // }


}
