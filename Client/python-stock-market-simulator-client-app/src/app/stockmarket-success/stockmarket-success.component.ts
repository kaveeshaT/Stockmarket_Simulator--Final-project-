import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Services/Api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stockmarket-success',
  templateUrl: './stockmarket-success.component.html',
  styleUrls: ['./stockmarket-success.component.css']
})
export class StockmarketSuccessComponent implements OnInit {

  status = "You Won!"

  player1 = "player 1"
  profit1 = 2300

  player2 = "player 2"
  profit2 = 2100

  player3 = "player 3"
  profit3 = 1800

  player4 = "player 4"
  profit4 = 1500
  gameRoomPlayers: any[] = [];
  val1: number;
  val2: number;
  val3: number;
  val4: any;

  constructor(private api: ApiService, private router: Router) { }

  ngOnInit() {
    const rn = sessionStorage.getItem('roomName');
    this.api.getRoomPlayers(rn).subscribe((data: any) => {
      this.gameRoomPlayers = data.gameRoomPlayers;
      console.log('gameRoomPlayers',data);
      // this.buySell = false;
    },(err)=>{},()=>{
      const ai = {
        "gameCount": 1,
        "highestProfit": 0.0,
        "name": "pasddasdsdasdasd",
        "playerId": 0,
        "remainigMoney": 1000.0,
        "winCount": 0
      }
      if (this.gameRoomPlayers.length < 4) {
        let val = 4 - this.gameRoomPlayers.length;
        console.log('gameRoomPlayers valval',val);
  
        for (let i = 0; i < val; i++) {
          // ai.remainigMoney = this.botScore();
          // ai.name = `AI${i}`
          this.gameRoomPlayers.push({
            "gameCount": 1,
            "highestProfit": 0.0,
            "name": `AI${i}`,
            "playerId": 0,
            "remainigMoney": this.botScore(),
            "winCount": 0
          });
        console.log('gameRoomPlayers this.gameRoomPlayers',this.gameRoomPlayers);
  
        }
      }
      this.gameRoomPlayers.sort(function (a, b) {
        return b.remainigMoney - a.remainigMoney;
      });
    });
   
  }
  botScore() {
    this.val1 = Math.random() * 10;
    this.val2 = Math.random() * 100;
    this.val3 = Math.random() * 500;
    console.log('this.val1', this.val1);
    console.log('this.val2', this.val2);
    console.log('this.val3', this.val3);
    console.log('value', 1000 + this.val1 + this.val2 + this.val3);
    this.val4 = 1000 + this.val1 + this.val2 + this.val3;
    this.val4 = Math.ceil(this.val4);
    console.log('this.val4', this.val4);
    return this.val4;
  }

}
