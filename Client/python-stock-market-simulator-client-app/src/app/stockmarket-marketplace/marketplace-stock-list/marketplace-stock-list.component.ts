import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-marketplace-stock-list',
  templateUrl: './marketplace-stock-list.component.html',
  styleUrls: ['./marketplace-stock-list.component.css']
})
export class MarketplaceStockListComponent implements OnInit {
@Input() stocks:any[];
@Input() Buystocks:any[];

  constructor() { }

  ngOnInit() {
  }
  getStockCurrentPrice(item) {
    const filt: any = this.stocks.filter(a => a.Id == item);
    console.log('cuurent stock',filt)
    return filt[0];
  }
}
