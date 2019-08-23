import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketMarketplaceComponent } from './stockmarket-marketplace.component';

describe('StockmarketMarketplaceComponent', () => {
  let component: StockmarketMarketplaceComponent;
  let fixture: ComponentFixture<StockmarketMarketplaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketMarketplaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketMarketplaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
