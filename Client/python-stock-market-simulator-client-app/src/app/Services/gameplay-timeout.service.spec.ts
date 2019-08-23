import { TestBed } from '@angular/core/testing';

import { GameplayTimeoutService } from './gameplay-timeout.service';

describe('GameplayTimeoutService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GameplayTimeoutService = TestBed.get(GameplayTimeoutService);
    expect(service).toBeTruthy();
  });
});
