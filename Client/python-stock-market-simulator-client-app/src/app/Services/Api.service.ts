import { Injectable } from '@angular/core';
import { Observable, Subject, BehaviorSubject, throwError, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class ApiService {
  errorStatus: boolean;
  headerDict = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Access-Control-Allow-Headers': 'Content-Type',
  }
  API_URL = environment.apiUrl;
  // const requestOptions = {                                                                                                                                                                                 
  //   headers: new Headers(headerDict), 
  // };
  constructor(private https: HttpClient,
  ) { }
  private get(host: string, path: string): Observable<any> {
    return this.https.get(`${host}/${path}/`)
      .pipe(catchError(err => throwError(this.formatError(err))));
  }

  private post(path: string, body: Object = {}): Observable<any> {
    return this.https
      .post(`${this.API_URL}/${path}/`, body)
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map((res: any) => res.json()));
  }

  getStocks(path): Observable<any> {
    return this.https.get(`http://localhost:8080/turn/${path}`, {
      headers:
        { 'Access-Control-Allow-Origin': '*' }
    })
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map(data => {
        console.log('dddddddddddd', data);
        return data;
      }));
  }
  getProfile(path): Observable<any> {
    return this.https.get(`http://localhost:8080/profile/${path}`, {
      headers:
        { 'Access-Control-Allow-Origin': '*' }
    })
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map(data => {
        console.log('dddddddddddd', data);
        return data;
      }));
  }
  getRoomPlayers(path): Observable<any> {
    return this.https.get(`http://localhost:8080/roomplayers/${path}`, {
      headers:
        { 'Access-Control-Allow-Origin': '*' }
    })
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map(data => {
        console.log('dddddddddddd', data);
        return data;
      }));
  }
  public getAllRooms(): Observable<any> {
    return this.https.get(`http://localhost:8080/getgamerooms`, {
      headers:
        { 'Access-Control-Allow-Origin': '*' }
    })
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map(data => {
        console.log('dddddddddddd', data);
        return data;
      }));

  }

  public createRooms(payload: any): Observable<any> {
    return this.https.post(`http://localhost:8080/gameroom`, payload, {
      headers:
        { 'Access-Control-Allow-Origin': '*' }
    })
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map(data => {
        console.log('dddddddddddd', data);
        return data;
      }));
  }
  public joinRooms(payload: any): Observable<any> {
    return this.https.post(`http://localhost:8080/joingame`, payload, {
      headers:
        { 'Access-Control-Allow-Origin': '*' }
    })
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map(data => {
        console.log('dddddddddddd', data);
        return data;
      }));
  }
  public Sell(payload: any): Observable<any> {
    return this.https.post(`http://localhost:8080/sell`, payload, {
      headers:
        { 'Access-Control-Allow-Origin': '*' }
    })
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map(data => {
        console.log('dddddddddddd', data);
        return data;
      }));
  }
  public Buy(payload: any): Observable<any> {
    return this.https.post(`http://localhost:8080/buy`, payload, {
      headers:
        { 'Access-Control-Allow-Origin': '*' }
    })
      .pipe(catchError(err => throwError(this.formatError(err))))
      .pipe(map(data => {
        console.log('dddddddddddd', data);
        return data;
      }));
  }
  private formatError(error: any) {
    console.error('ApiService::formatError', error);
    this.errorStatus = true;
    console.log('Server Erorr');
    return Observable.throw(error.json());
  }
}