import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketLoginComponent } from './stockmarket-login.component';

describe('StockmarketLoginComponent', () => {
  let component: StockmarketLoginComponent;
  let fixture: ComponentFixture<StockmarketLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
