import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketHeaderComponent } from './stockmarket-header.component';

describe('StockmarketHeaderComponent', () => {
  let component: StockmarketHeaderComponent;
  let fixture: ComponentFixture<StockmarketHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
