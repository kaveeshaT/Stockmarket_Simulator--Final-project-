import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule }    from '@angular/common/http';
// import { HttpClient } from '@angular/common/http';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { StockmarketLogoutComponent } from './stockmarket-logout/stockmarket-logout.component';
import { MarketplaceUserOperationsComponent } from './stockmarket-marketplace/marketplace-user-operations/marketplace-user-operations.component';
import { MarketplaceBuySellComponent } from './stockmarket-marketplace/marketplace-buy-sell/marketplace-buy-sell.component';
import { StockmarketHeaderComponent } from './stockmarket-header/stockmarket-header.component';


import { BuySellService } from './Services/buy-sell.service';
import { GameplayTimeoutService } from './Services/gameplay-timeout.service';
import { LoginHandlerService } from './Services/login-handler.service';
import { ApiService } from './Services/Api.service';
import { MarketplaceCompanyListComponent } from './stockmarket-marketplace/marketplace-company-list/marketplace-company-list.component';
import { MarketplaceHeaderComponent } from './stockmarket-marketplace/marketplace-header/marketplace-header.component';


import { ChartsModule } from 'ng2-charts';
import { StockmarketLobbyComponent } from './stockmarket-lobby/stockmarket-lobby.component';
import { StockmarketHowtoComponent } from './stockmarket-howto/stockmarket-howto.component';


@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    StockmarketLogoutComponent,
    MarketplaceUserOperationsComponent,
    MarketplaceBuySellComponent,
    StockmarketHeaderComponent,
    MarketplaceCompanyListComponent,
    MarketplaceHeaderComponent,
    StockmarketLobbyComponent,
    StockmarketHowtoComponent
    
    
 
    
  ],
  imports: [
    ChartsModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    // HttpClient,
    HttpClientModule,
    ReactiveFormsModule
    // HttpHeaders
  ],
  providers: [
    BuySellService,
    GameplayTimeoutService,
    LoginHandlerService,
    ApiService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
