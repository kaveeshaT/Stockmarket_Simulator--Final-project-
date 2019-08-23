package server.data.service.Core.Interface;

import server.data.service.Actors.BrokerActor.*;
import server.data.service.Actors.GameLobbyActor;

import java.io.Serializable;

public interface BrokerMessage {
    class ActionPerformed implements Serializable {
        private final String description;

        public ActionPerformed(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

    }
    class BuyStock implements Serializable {
        private final StockBroker stockBroker;

        public BuyStock(StockBroker stockBroker) {
            this.stockBroker = stockBroker;
        }

        public StockBroker getStockBroker() {
            return stockBroker;
        }
    }
    class SellStock implements Serializable {
        private final StockBroker stockBroker;

        public SellStock(StockBroker stockBroker) {
            this.stockBroker = stockBroker;
        }

        public StockBroker getStockBroker() {
            return stockBroker;
        }
    }
}
