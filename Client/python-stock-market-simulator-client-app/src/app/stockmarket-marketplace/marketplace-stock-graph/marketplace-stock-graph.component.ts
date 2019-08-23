import { Component, OnInit, Input } from '@angular/core';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import { ApiService } from 'src/app/Services/Api.service';
import { interval } from 'rxjs/internal/observable/interval';
@Component({
  selector: 'app-marketplace-stock-graph',
  templateUrl: './marketplace-stock-graph.component.html',
  styleUrls: ['./marketplace-stock-graph.component.css']
})
export class MarketplaceStockGraphComponent implements OnInit {

  @Input() stocks: any[];

  prices: number[] = [];
  public lineChartData: ChartDataSets[] = [
    { data: [65, 59, 80, 81, 56, 55, 40, 56, 55, 40], label: 'FaceBook' },
    { data: [54, 59, 87, 33, 44, 67, 40, 56, 55, 40], label: 'Google' },
    { data: [45, 59, 78, 81, 56, 55, 40, 56, 55, 40], label: 'Twitter' },
    { data: [65, 59, 80, 81, 56, 55, 40, 56, 55, 40], label: 'Microsoft' },
    { data: [43, 59, 87, 81, 44, 86, 23, 56, 55, 40], label: 'Jhon Keels' },
    { data: [76, 59, 80, 32, 56, 55, 40, 56, 55, 40], label: 'Sampath Bank' },
    { data: [65, 59, 80, 81, 56, 55, 40, 56, 55, 40], label: 'LOLC Finance' },
    { data: [87, 59, 87, 81, 44, 55, 40, 56, 55, 40], label: 'DFCC Bank' },
    { data: [65, 59, 80, 23, 56, 55, 40, 56, 55, 40], label: 'LECO' },
    { data: [65, 59, 80, 81, 44, 55, 40, 56, 55, 40], label: 'Dialog Axiata' },
    { data: [65, 59, 20, 81, 56, 87, 40, 56, 55, 40], label: 'Mobitel' },
    { data: [65, 59, 80, 81, 44, 55, 40, 56, 55, 40], label: 'Lanka Bell' },
    { data: [63, 56, 81, 85, 46, 57, 42, 59, 57, 43], label: 'Mas Holdings' },
    { data: [63, 56, 81, 85, 46, 57, 42, 59, 57, 43], label: 'Toyota' },
    { data: [63, 56, 81, 85, 46, 57, 42, 59, 57, 43], label: 'Honda' },
    { data: [63, 56, 81, 85, 46, 57, 42, 59, 57, 43], label: 'Richard Pieris' }
  ];
  public lineChartLabels: Label[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
  public lineChartOptions = {
    responsive: true,
  };
  public lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,0,0,0.3)',
    },
  ];
  public lineChartLegend = true;
  public lineChartType = 'line';
  public lineChartPlugins = [];

  constructor(private api: ApiService) { }

  ngOnInit() {
  // const dataChart =   { data: [], label: 'Series A' }
  //   this.stocks.forEach(a => {
  //     this.lineChartData = [];
  //     a.forEach(a => {
  //       dataChart.data.push(a.Price);
  //       dataChart.label = a.DisplayName;
  //       this.lineChartData.push(dataChart);
  //     })



  //   })

  }

}
