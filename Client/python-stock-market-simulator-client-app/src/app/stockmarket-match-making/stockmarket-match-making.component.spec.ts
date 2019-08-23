import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketMatchMakingComponent } from './stockmarket-match-making.component';

describe('StockmarketMatchMakingComponent', () => {
  let component: StockmarketMatchMakingComponent;
  let fixture: ComponentFixture<StockmarketMatchMakingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketMatchMakingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketMatchMakingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
