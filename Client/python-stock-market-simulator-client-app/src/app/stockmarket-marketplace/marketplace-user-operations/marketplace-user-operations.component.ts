import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-marketplace-user-operations',
  templateUrl: './marketplace-user-operations.component.html',
  styleUrls: ['./marketplace-user-operations.component.css']
})
export class MarketplaceUserOperationsComponent implements OnInit {
@Output() TradeEvent = new EventEmitter();
@Input() accountBalance:any;
  constructor() { }

  ngOnInit() {
  }

  startTrading(){
    this.TradeEvent.next(true);
  }

}
