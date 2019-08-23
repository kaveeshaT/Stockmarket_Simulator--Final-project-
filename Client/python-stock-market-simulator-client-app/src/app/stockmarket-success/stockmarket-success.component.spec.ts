import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketSuccessComponent } from './stockmarket-success.component';

describe('StockmarketSuccessComponent', () => {
  let component: StockmarketSuccessComponent;
  let fixture: ComponentFixture<StockmarketSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
