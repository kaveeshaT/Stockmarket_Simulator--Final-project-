package server.data.service.Actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import server.data.service.Core.Interface.EventsModeling;
import server.data.service.Core.Interface.MarketMessages;
import server.data.service.Core.Interface.Sectors;
import server.data.service.Core.Interface.Stock;
import server.data.service.Core.Services.StockMarketModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarketActor extends AbstractActor {
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static class StockTurns {
        private final List<Stock> stocks;

        public StockTurns() {
            this.stocks = new ArrayList<>();
        }

        public StockTurns(List<Stock> stocks) {
            this.stocks = stocks;
        }

        public List<Stock> getStocks() {
            return stocks;
        }
    }
    static public Props props() {
        return Props.create(MarketActor.class);
    }
    private StockMarketModel stockMarketModel = new StockMarketModel();
    private List<Sectors> sectors = new ArrayList<Sectors>();

    private List<Stock> stock = new ArrayList<Stock>();
    private List<List<Stock>> stockTurns = new ArrayList<List<Stock>>();
    private EventsModeling event = new EventsModeling();

    private Random random = new Random();

    public MarketActor() {
        sectors = stockMarketModel.GetSectors();
        stock = stockMarketModel.GetStocks();
        CalculateTurnPrices();

    }

    public void CalculateTurnPrices() {
        for (int i = 0; i < 10; i++) {
            int sectorId = GenerateSectorTrend();
            int st = 0;
            int StockValue = CalculateValues();
            List<Stock> stocka = new ArrayList<Stock>();

            for (Stock stk : stock) {

                if (stk.SectorId == sectorId) {
                    st = stockMarketModel.SectorTrendMarketComponent();
                }
                StockValue = StockValue + st;
                if (StockValue < 0) {
                    StockValue = 0;
                }
                stk.Price = (float) ((stk.Price / 100) * (StockValue + 100));
                Stock s = new Stock(stk.Id, stk.Name, stk.DisplayName, stk.SectorId, stk.Price);
                stocka.add(s);
            }
            stockTurns.add(stocka);



        }

    }

    public List<Stock> getStocksCalculated(String trun) {
        return stockTurns.get(Integer.parseInt(trun));
    }

    private void GenerateSectorEvent() {
        int rn = random.nextInt(3);
        if (rn == 0) {
            event = stockMarketModel.GetRandomSectorEvent();
        } else if (rn == 1) {
            event = stockMarketModel.GetRandomStockEvent();
        } else {
            event = null;
        }
    }

    private int GenerateSectorTrend() {
        return random.nextInt(4) + 1;
    }

    public int CalculateValues() {
        GenerateSectorEvent();
        int rm = stockMarketModel.RandomMarketComponent();
        int gt = stockMarketModel.GeneralTrendMarketComponent();
//        int st = stockMarketModel.SectorTrendMarketComponent();


        int rn;
        if (event == null) {
            rn = 0;
        } else {
            if (event.MaxRange < 0 || event.MinRange < 0) {
                rn = random.nextInt(Math.abs(event.MinRange) - Math.abs(event.MaxRange)) + Math.abs(event.MaxRange);
                rn = rn * -1;
            } else {
                rn = random.nextInt(event.MaxRange - event.MinRange) + event.MinRange;

            }
        }
//        CalculateTurnPrices();

        return rm + gt + rn;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MarketMessages.GetTurn.class, getTurn -> {
                            getSender().tell(new StockTurns(getStocksCalculated(getTurn.getTurn())), getSelf());
                }
                )
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
