import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-marketplace-header',
  templateUrl: './marketplace-header.component.html',
  styleUrls: ['./marketplace-header.component.css']
})
export class MarketplaceHeaderComponent implements OnInit {

  @Input() timer:any;
  @Input() turn:any;
  constructor() { }

  ngOnInit() {
  }

}
