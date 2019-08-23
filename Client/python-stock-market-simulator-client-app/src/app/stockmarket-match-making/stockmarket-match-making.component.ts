import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Services/Api.service';
import { Router } from '@angular/router';
// import { APIServiceService } from '../Services/API-service.service';

@Component({
  selector: 'app-stockmarket-match-making',
  templateUrl: './stockmarket-match-making.component.html',
  styleUrls: ['./stockmarket-match-making.component.css']
})
export class StockmarketMatchMakingComponent implements OnInit {



  gameRooms: any[];
  createRoom: any;
  createRoomPayload = {
    playerId: 0,
    roomName: ''
  };

  constructor(
    private apiService: ApiService,
    private router: Router

  ) { }

  ngOnInit() {
    this.apiService.getAllRooms().subscribe((states: any) => {
      this.gameRooms = states.gameRooms;
    });
  }

  getPositive(val: number) {
    return val > 0;
  }

  createARoom(payload) {
    const user: any = JSON.parse(sessionStorage.getItem('currentUsername'));
    console.log(user);
    this.createRoomPayload.playerId = user.player.playerId;
    this.createRoomPayload.roomName = this.createRoom;
    sessionStorage.setItem('roomName',this.createRoom);
    this.apiService.createRooms(this.createRoomPayload).subscribe((states: any) => {
      this.gameRooms = states.gameRooms;
    }, (err) => {
      console.log(err);
    }, () => {
      this.router.navigateByUrl('/marketplace');
    });
  }
  joinARoom(item) {
    const user: any = JSON.parse(sessionStorage.getItem('currentUsername'));
    console.log(user);
    this.createRoomPayload.playerId = user.player.playerId;
    this.createRoomPayload.roomName = item.name;
    sessionStorage.setItem('roomName',this.createRoom);
    this.apiService.joinRooms(this.createRoomPayload).subscribe((states: any) => {
      this.gameRooms = states.gameRooms;
    }, (err) => {
      console.log(err);
    }, () => {
      this.router.navigateByUrl('/marketplace');
    });
  }
}
