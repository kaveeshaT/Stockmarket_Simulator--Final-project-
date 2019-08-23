import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketUserPortfolioComponent } from './stockmarket-user-portfolio.component';

describe('StockmarketUserPortfolioComponent', () => {
  let component: StockmarketUserPortfolioComponent;
  let fixture: ComponentFixture<StockmarketUserPortfolioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketUserPortfolioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketUserPortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
