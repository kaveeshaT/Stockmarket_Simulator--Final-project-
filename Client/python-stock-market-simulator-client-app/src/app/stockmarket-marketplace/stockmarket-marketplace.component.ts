import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs/internal/observable/interval';
import { ApiService } from '../Services/Api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stockmarket-marketplace',
  templateUrl: './stockmarket-marketplace.component.html',
  styleUrls: ['./stockmarket-marketplace.component.css']
})
export class StockmarketMarketplaceComponent implements OnInit {

  stocks: any[];
  stocksList: any[] = [];
  buySell = false;
  accountBalance:any;
  time: any;
  stocksOwn: any;
  user: any;
  turn: number;
  constructor(private api: ApiService,private router:Router) { }

  ngOnInit() {
    this.api.getStocks(1).subscribe((data: any) => {
      this.stocks = data.stocks;
      this.stocksList.push(this.stocks);
      console.log(data.stocks);
    });
    this.timer()
    this.stocksOwn = JSON.parse(sessionStorage.getItem('buyingHisty'));
    console.log('test test',this.stocksOwn);
    this.user = JSON.parse(sessionStorage.getItem('currentUsername'));
    this.api.getProfile(this.user.player.name).subscribe((data: any) => {
      this.accountBalance = data.remainigMoney;
         console.log(data);
         // this.buySell = false;
       })
     this.turn = 1;
    interval(10 * 1000).subscribe(
      (value: number) => {
        this.timer();
        this.turn++;
        console.log('test test');
        this.stocksOwn = JSON.parse(sessionStorage.getItem('buyingHisty'));
        console.log('test test',this.stocksOwn);
        if (this.turn < 10) {
          this.api.getStocks(this.turn).subscribe((data: any) => {
            this.stocks = data.stocks;
            this.stocksList.push(this.stocks);
            console.log('stocksList',this.stocksList);
            // this.buySell = false;
          });
         
        }else{
          this.router.navigateByUrl('/success')
        }
        this.api.getProfile(this.user.player.name).subscribe((data: any) => {
       this.accountBalance = data.remainigMoney;
          console.log(data);
          // this.buySell = false;
        })

      },
      (error: any) => {
        console.log('error');
      },
      () => {
        console.log('observable completed !');
      }
    );
  }
  public timer() {
    var sec = 10;
    const that = this;
    var timer = setInterval(function () {
      that.time = sec;
      console.log('Timer', that.time);
      sec--;
      if (sec < 0) {
        clearInterval(timer);
      }
    }, 1000);
  }

  OpenBuySell() {
    console.log('popopopop')
    this.buySell = !this.buySell;
  }
  getStockCurrentPrice(item) {
    const filt: any = this.stocks.filter(a => a.Id == item);
    return filt.Price;
  }

}
