package server.data.service.Actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import server.data.service.Core.Interface.BankMessage;
import server.data.service.Core.Interface.BrokerMessage;
import server.data.service.Core.Services.DBQueries;

public class BankActor extends AbstractActor {
    static public Props props() {
        return Props.create(BankActor.class);
    }

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private DBQueries dbQueries = new DBQueries();


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(BankMessage.BuyTransaction.class, buyStock -> {
                            dbQueries.UpdateBuyPlayerWallet(buyStock.getStockBroker());
                        }
                ).match(BankMessage.SellTransaction.class, buyStock -> {
                            dbQueries.UpdateSellPlayerWallet(buyStock.getStockBroker());
                        }
                ).matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
