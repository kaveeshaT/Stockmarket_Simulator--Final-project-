import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StockmarketLoginComponent } from './stockmarket-login/stockmarket-login.component';
import { StockmarketMenuComponent } from './stockmarket-menu/stockmarket-menu.component';
import { StockmarketMatchMakingComponent } from './stockmarket-match-making/stockmarket-match-making.component';
import { StockmarketMarketplaceComponent } from './stockmarket-marketplace/stockmarket-marketplace.component';
import { StockmarketUserPortfolioComponent } from './stockmarket-user-portfolio/stockmarket-user-portfolio.component';
import { StockmarketSuccessComponent } from './stockmarket-success/stockmarket-success.component';
import { StockmarketSignupComponent } from './stockmarket-signup/stockmarket-signup.component';
import { MarketplaceBuySellComponent } from './stockmarket-marketplace/marketplace-buy-sell/marketplace-buy-sell.component';
import { MarketplaceStockGraphComponent } from './stockmarket-marketplace/marketplace-stock-graph/marketplace-stock-graph.component';
import { MarketplaceStockListComponent } from './stockmarket-marketplace/marketplace-stock-list/marketplace-stock-list.component';
import { MarketplaceUserOperationsComponent } from './stockmarket-marketplace/marketplace-user-operations/marketplace-user-operations.component';
import { StockmarketLobbyComponent } from './stockmarket-lobby/stockmarket-lobby.component';
import { StockmarketHowtoComponent } from './stockmarket-howto/stockmarket-howto.component';


const routes: Routes = [{
  path: '',
  component: StockmarketLoginComponent
},

{
  path: 'menu',
  component: StockmarketMenuComponent
},
{
  path: 'matchmaking',
  component: StockmarketMatchMakingComponent
},
{
  path: 'marketplace',
  component: StockmarketMarketplaceComponent,
  children: [
    { path: 'marketplace/buy-sell', component: MarketplaceBuySellComponent },
    { path: 'marketplace/graph', component: MarketplaceStockGraphComponent },
    { path: 'marketplace/list', component: MarketplaceStockListComponent },
    { path: 'marketplace/opt', component: MarketplaceUserOperationsComponent }
  ]
},
{
  path: 'profile',
  component: StockmarketUserPortfolioComponent
},
{
  path: 'success',
  component: StockmarketSuccessComponent
},
{
  path: 'signup',
  component: StockmarketSignupComponent
},
{
  path: 'lobby',
  component: StockmarketLobbyComponent
},
{
  path: 'howto',
  component: StockmarketHowtoComponent
}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  // MarketplaceUserOperationsComponent
 }
export const routingComponents = [
  StockmarketLoginComponent,
  StockmarketMenuComponent,
  StockmarketMatchMakingComponent,
  StockmarketMarketplaceComponent,
  StockmarketUserPortfolioComponent,
  StockmarketSuccessComponent,
  StockmarketSignupComponent,
  MarketplaceUserOperationsComponent,
  MarketplaceBuySellComponent,
  MarketplaceStockGraphComponent,
  MarketplaceStockListComponent,
  StockmarketLobbyComponent,
  StockmarketHowtoComponent
]
