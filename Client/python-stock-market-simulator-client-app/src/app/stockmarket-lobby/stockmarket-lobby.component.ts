import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-stockmarket-lobby',
  templateUrl: './stockmarket-lobby.component.html',
  styleUrls: ['./stockmarket-lobby.component.css']
})
export class StockmarketLobbyComponent implements OnInit {

  players=[
    {
      name: 'player 1',
      
    },
    {
      name: 'player 2',
      
    },
    {
      name: 'player 3',
      
    },
    {
      name: 'player 4',
      
    },
  ]

  constructor() { }

  ngOnInit() {
  }

}
