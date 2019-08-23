import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketplaceUserOperationsComponent } from './marketplace-user-operations.component';

describe('MarketplaceUserOperationsComponent', () => {
  let component: MarketplaceUserOperationsComponent;
  let fixture: ComponentFixture<MarketplaceUserOperationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketplaceUserOperationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketplaceUserOperationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
