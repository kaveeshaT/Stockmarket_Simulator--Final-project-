package server.data.service.Core.Services;

import server.data.service.Core.Interface.EventsModeling;
import server.data.service.Core.Interface.Sectors;
import server.data.service.Core.Interface.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class StockMarketModel {
    private Random random = new Random();
    private List<EventsModeling> SectorEvent = new ArrayList<EventsModeling>();
    private List<EventsModeling> StockEvent = new ArrayList<EventsModeling>();
    private List<Sectors> sectors = new ArrayList<Sectors>();
    private List<Stock> stock = new ArrayList<Stock>();

    public StockMarketModel() {
        SectorEvent.add(new EventsModeling("BOOM", 1, 5, (float) 0.5, 2, 5));
        SectorEvent.add(new EventsModeling("BUST", -5, -1, (float) 0.5, 2, 5));

        StockEvent.add(new EventsModeling("PROFIT_WARNING ", 2, 3, (float) 0.5, 1, 7));
        StockEvent.add(new EventsModeling("PROFIT_WARNING ", 2, 3, (float) 0.5, 1, 7));

        StockEvent.add(new EventsModeling("TAKE_OVER", -5, -1, (float) 0.25, 1, 7));
        StockEvent.add(new EventsModeling("SCANDAL", -6, -3, (float) 0.25, 1, 7));

        sectors.add(new Sectors(1, "Technology"));
        sectors.add(new Sectors(2, "Finance"));
        sectors.add(new Sectors(3, "ConsumerServices"));
        sectors.add(new Sectors(4, "Manufacturing"));

        stock.add(new Stock(5, "FaceBook", "FaceBook", 1,30));
        stock.add(new Stock(6, "Gooagle", "Google", 1,30));
        stock.add(new Stock(7, "Twitter", "Twitter", 1,30));
        stock.add(new Stock(8, "Microsoft", "Microsoft", 1,30));

        stock.add(new Stock(9, "Jhonkeels", "Jhon keels", 2,20));
        stock.add(new Stock(10, "SampathBank", "Sampath Bank", 2,20));
        stock.add(new Stock(11, "LOLCfinance", "LOLC finance", 2,20));
        stock.add(new Stock(12, "DFCCBankPLC", "DFCC Bank PLC", 2,20));

        stock.add(new Stock(13, "LankaElectricityCompany", "Lanka Electricity Company", 3,15));
        stock.add(new Stock(14, "DialogAxiata", "Dialog Axiata", 3,15));
        stock.add(new Stock(15, "Mobitel", "Mobitel", 3,15));
        stock.add(new Stock(16, "LankaBell", "Lanka Bell", 3,15));

        stock.add(new Stock(17, "MASHoldings", "MAS Holdings", 4,10));
        stock.add(new Stock(18, "Toyota", "Toyota", 4,10));
        stock.add(new Stock(19, "Honda", "Honda", 4,10));
        stock.add(new Stock(20, "Richardpieris", "Richard Pieris", 4,10));


    }


    public int RandomMarketComponent() {
        return random.nextInt(2 + 2) - 2;
    }

    public int SectorTrendMarketComponent() {
        return random.nextInt(3 + 3) - 3;
    }

    public int GeneralTrendMarketComponent() {
        return random.nextInt(3 + 3) - 3;
    }

    public EventsModeling GetRandomSectorEvent() {
        int rn = random.nextInt(2);
        return SectorEvent.get(rn);
    }

    public EventsModeling GetRandomStockEvent() {
        int rn = random.nextInt(4);
        return StockEvent.get(rn);
    }

    public List<Sectors> GetSectors() {
        return sectors;
    }

    public List<Stock> GetStocks() {
        return stock;
    }

}
