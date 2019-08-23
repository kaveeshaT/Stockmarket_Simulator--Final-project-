import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { BuySellModel } from '../../models/buy-sell';
import { ApiService } from 'src/app/Services/Api.service';



@Component({
  selector: 'app-marketplace-buy-sell',
  templateUrl: './marketplace-buy-sell.component.html',
  styleUrls: ['./marketplace-buy-sell.component.css']
})
export class MarketplaceBuySellComponent implements OnInit {


  @Output() closePop = new EventEmitter();
  @Input() stocks: any;
  tempStock: any;
  Companies: any;
  stockAmount: any;
  public buyModel: BuySellModel;
  user: any
  buyingHisty: any[] = [];
  sellingHisty: any[] = [];
  stocksOwn: any;


  constructor(private api: ApiService) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('currentUsername'));
    console.log('user', this.user.player.playerId);
    this.tempStock = this.stocks;
    this.buyModel = {
      playerId: 0,
      roomId: 0,
      stockAmount: 0,
      stockId: null,
      totalPrice: 0
    }
  }
  CloseWindow() {
    this.closePop.emit();
  }
  buy() {
    const roomId: any = sessionStorage.getItem('roomId') || 0;
    this.buyModel.playerId = this.user.player.playerId;
    this.buyModel.stockAmount = this.stockAmount;
    this.buyModel.stockId = this.Companies.Id;
    this.buyModel.totalPrice = this.stockAmount * this.Companies.Price;
    this.buyModel.roomId = 0;
    this.stocksOwn = JSON.parse(sessionStorage.getItem('buyingHisty'));
    this.buyingHisty = this.stocksOwn;
    this.buyingHisty.push(this.buyModel);
    sessionStorage.setItem('buyingHisty', JSON.stringify(this.buyingHisty));
    this.api.Buy(this.buyModel).subscribe((data: any) => {
      console.log(data);
    }, (err) => {
      console.log(err);
    }, () => {
      alert('Brought the stock')
    })
  }
  sell() {
    const roomId: any = sessionStorage.getItem('roomId') || 0;
    this.buyModel.playerId = this.user.player.playerId;
    this.buyModel.stockAmount = this.stockAmount;
    this.buyModel.stockId = this.Companies.Id;
    this.buyModel.totalPrice = this.stockAmount * this.Companies.Price;
    this.buyModel.roomId = 0;
    this.sellingHisty.push(this.buyModel);
    sessionStorage.setItem('sellingHisty', JSON.stringify(this.sellingHisty));
    this.api.Sell(this.buyModel).subscribe((data: any) => {
      console.log(data);
    }, (err) => {
      console.log(err);
    }, () => {
      alert('Sold the stocks')
    })
  }
  setData(item) {
    console.log(item)
    this.Companies = item;
  }
}
