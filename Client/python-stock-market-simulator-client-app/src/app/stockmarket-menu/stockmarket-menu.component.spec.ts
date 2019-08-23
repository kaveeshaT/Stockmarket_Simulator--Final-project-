import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketMenuComponent } from './stockmarket-menu.component';

describe('StockmarketMenuComponent', () => {
  let component: StockmarketMenuComponent;
  let fixture: ComponentFixture<StockmarketMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
