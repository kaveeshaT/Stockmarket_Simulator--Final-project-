import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketplaceStockListComponent } from './marketplace-stock-list.component';

describe('MarketplaceStockListComponent', () => {
  let component: MarketplaceStockListComponent;
  let fixture: ComponentFixture<MarketplaceStockListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketplaceStockListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketplaceStockListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
