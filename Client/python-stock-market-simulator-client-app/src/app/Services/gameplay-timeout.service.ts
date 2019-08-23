import { Injectable } from '@angular/core';
import { interval } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameplayTimeoutService {

  constructor() { }

  gameDuration() {
    console.log('testing');
    interval(10 * 1000).subscribe(
      (value: number) => {
        value = 100;
        console.log('test test');
      },
      (error: any) => {
        console.log('error');
      },
      () => {
        console.log('observable completed !');
      }
    );
  }
}

// turn(Payload): Observable<ApiResponse> {
//   return this.http.post<ApiResponse>('http://localhost:8080/' + 'token/generate-token', loginPayload).pipe(catchError(val => of(val)));
// }

