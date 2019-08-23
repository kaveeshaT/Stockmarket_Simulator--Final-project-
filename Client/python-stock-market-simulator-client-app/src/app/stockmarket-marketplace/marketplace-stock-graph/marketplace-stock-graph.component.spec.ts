import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketplaceStockGraphComponent } from './marketplace-stock-graph.component';

describe('MarketplaceStockGraphComponent', () => {
  let component: MarketplaceStockGraphComponent;
  let fixture: ComponentFixture<MarketplaceStockGraphComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketplaceStockGraphComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketplaceStockGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
