import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-marketplace-company-list',
  templateUrl: './marketplace-company-list.component.html',
  styleUrls: ['./marketplace-company-list.component.css']
})
export class MarketplaceCompanyListComponent implements OnInit {
  @Input() stocks:any[];

  constructor() { }

  ngOnInit() {
  }

}
