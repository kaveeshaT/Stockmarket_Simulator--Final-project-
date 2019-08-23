import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketSignupComponent } from './stockmarket-signup.component';

describe('StockmarketSignupComponent', () => {
  let component: StockmarketSignupComponent;
  let fixture: ComponentFixture<StockmarketSignupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketSignupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
