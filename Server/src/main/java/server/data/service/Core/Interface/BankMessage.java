package server.data.service.Core.Interface;

import server.data.service.Actors.BrokerActor.*;

import java.io.Serializable;

public interface BankMessage {
    class BuyTransaction implements Serializable {
        private final StockBroker stockBroker;
        public BuyTransaction(StockBroker stockBroker) {
            this.stockBroker = stockBroker;
        }

        public StockBroker getStockBroker() {
            return stockBroker;
        }
    }
    class SellTransaction implements Serializable {
        private final StockBroker stockBroker;
        public SellTransaction(StockBroker stockBroker) {
            this.stockBroker = stockBroker;
        }

        public StockBroker getStockBroker() {
            return stockBroker;
        }
    }
}
