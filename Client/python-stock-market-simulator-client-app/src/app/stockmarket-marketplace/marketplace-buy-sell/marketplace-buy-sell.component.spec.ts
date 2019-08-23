import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketplaceBuySellComponent } from './marketplace-buy-sell.component';

describe('MarketplaceBuySellComponent', () => {
  let component: MarketplaceBuySellComponent;
  let fixture: ComponentFixture<MarketplaceBuySellComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketplaceBuySellComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketplaceBuySellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
