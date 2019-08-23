package server.data.service.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import server.data.service.Core.Interface.BankMessage;
import server.data.service.Core.Interface.BrokerMessage;
import server.data.service.Core.Services.DBQueries;

public class BrokerActor extends AbstractActor {
    static public Props props() {
        return Props.create(BrokerActor.class);
    }
    ActorRef bankActor = getContext().actorOf(Props.create(BankActor.class), "bankActor");
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private DBQueries dbQueries = new DBQueries();

    public static class StockBroker {
        private int playerId;
        private int roomId;
        private int stockId;
        private int stockAmount;
        private float totalPrice;


        public StockBroker(int playerId, int roomId, int stockId, int stockAmount, float totalPrice) {
            this.playerId = playerId;
            this.roomId = roomId;
            this.stockId = stockId;
            this.stockAmount = stockAmount;
            this.totalPrice = totalPrice;
        }

        public StockBroker() {
            this.playerId = 0;
            this.roomId = 0;
            this.stockId = 0;
            this.stockAmount = 0;
            this.totalPrice = 0;
        }

        public int getPlayerId() {
            return playerId;
        }

        public int getRoomId() {
            return roomId;
        }

        public int getStockId() {
            return stockId;
        }

        public int getStockAmount() {
            return stockAmount;
        }

        public float getTotalPrice() {
            return totalPrice;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(BrokerMessage.BuyStock.class, buyStock -> {
                            dbQueries.BuyStock(buyStock.getStockBroker());
                            bankActor.forward(new BankMessage.BuyTransaction(buyStock.getStockBroker()), getContext());
                            getSender().tell(new BrokerMessage.ActionPerformed(
                                    String.format("Brought the %s Stock.", buyStock.getStockBroker().getStockId())), getSelf());
                        }
                ).match(BrokerMessage.SellStock.class, buyStock -> {
                            dbQueries.SellStock(buyStock.getStockBroker());
                            bankActor.forward(new BankMessage.SellTransaction(buyStock.getStockBroker()), getContext());
                            getSender().tell(new BrokerMessage.ActionPerformed(
                                    String.format("Sold %s Stock.", buyStock.getStockBroker().getStockId())), getSelf());
                        }
                ).matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
