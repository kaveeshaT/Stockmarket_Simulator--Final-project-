import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketplaceCompanyListComponent } from './marketplace-company-list.component';

describe('MarketplaceCompanyListComponent', () => {
  let component: MarketplaceCompanyListComponent;
  let fixture: ComponentFixture<MarketplaceCompanyListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketplaceCompanyListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketplaceCompanyListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
