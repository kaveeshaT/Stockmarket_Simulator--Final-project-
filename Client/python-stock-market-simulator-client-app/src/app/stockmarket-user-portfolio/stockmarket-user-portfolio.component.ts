import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-stockmarket-user-portfolio',
  templateUrl: './stockmarket-user-portfolio.component.html',
  styleUrls: ['./stockmarket-user-portfolio.component.css']
})
export class StockmarketUserPortfolioComponent implements OnInit {

  name = "Player name"
  profit = 2300
  winRate =51
  constructor() { }

  ngOnInit() {
  }

}
