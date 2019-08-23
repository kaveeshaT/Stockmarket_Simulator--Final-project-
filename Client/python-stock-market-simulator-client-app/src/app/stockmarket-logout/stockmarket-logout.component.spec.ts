import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketLogoutComponent } from './stockmarket-logout.component';

describe('StockmarketLogoutComponent', () => {
  let component: StockmarketLogoutComponent;
  let fixture: ComponentFixture<StockmarketLogoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketLogoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketLogoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
