import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { LoginHandlerService } from '../../app/Services/login-handler.service';
import { Router, ActivatedRoute } from '@angular/router';
import { GameplayTimeoutService } from '../Services/gameplay-timeout.service';
import { LoginHandlerService } from '../Services/login-handler.service';
import { map, catchError, first } from 'rxjs/operators';
import { User } from '../models/user';

@Component({
  selector: 'app-stockmarket-login',
  templateUrl: './stockmarket-login.component.html',
  styleUrls: ['./stockmarket-login.component.css']
})

export class StockmarketLoginComponent implements OnInit {

  userName: any = '';
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  invalidLogin: boolean = false;


  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private loginService: LoginHandlerService
  ) {

  }



  ngOnInit() {


  }


  onSubmit() {
    console.log('new userrrrr', this.userName);

    this.loading = true;
    this.loginService.login(this.userName)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
        },
        error => {
          console.log('new userrrrr');
          this.loading = false;
        },()=>{
          this.router.navigateByUrl('/menu');
        });


  }


}