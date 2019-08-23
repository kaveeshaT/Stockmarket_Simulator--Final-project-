import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketHowtoComponent } from './stockmarket-howto.component';

describe('StockmarketHowtoComponent', () => {
  let component: StockmarketHowtoComponent;
  let fixture: ComponentFixture<StockmarketHowtoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketHowtoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketHowtoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
