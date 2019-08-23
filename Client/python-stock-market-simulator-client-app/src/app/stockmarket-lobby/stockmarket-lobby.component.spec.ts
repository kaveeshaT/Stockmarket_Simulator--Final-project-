import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockmarketLobbyComponent } from './stockmarket-lobby.component';

describe('StockmarketLobbyComponent', () => {
  let component: StockmarketLobbyComponent;
  let fixture: ComponentFixture<StockmarketLobbyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockmarketLobbyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockmarketLobbyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
