package server.data.service.Actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import server.data.service.Core.Interface.PlayerRegistryMessages;
import server.data.service.Core.Services.DBQueries;

import java.util.ArrayList;
import java.util.List;

public class PlayerRegistryActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    //#user-case-classes
    public static class Player {
        private final String name;
        private final int playerId;
        private final String gameLobbyId;

        public Player() {
            this.name = "";
            this.gameLobbyId = "";
            this.playerId = 1;
        }

        public Player(String name, int playerId, String gameLobbyId) {
            this.name = name;
            this.playerId = playerId;
            this.gameLobbyId = gameLobbyId;
        }

        public String getName() {
            return name;
        }

        public int getPlayerId() {
            return playerId;
        }

        public String getGameLobbyId() {
            return gameLobbyId;
        }
    }

    public static class Players {
        private final List<Player> users;

        public Players() {
            this.users = new ArrayList<>();
        }

        public Players(List<Player> players) {
            this.users = players;
        }

        public List<Player> getUsers() {
            return users;
        }
    }

    public static class Profile {
        private final String name;
        private final float remainigMoney;
        private final float highestProfit;
        private final int gameCount;
        private final int winCount;
        private final int playerId;

        public Profile(String name, float remainigMoney, float highestProfit, int gameCount, int winCount, int playerId) {
            this.name = name;
            this.remainigMoney = remainigMoney;
            this.highestProfit = highestProfit;
            this.gameCount = gameCount;
            this.winCount = winCount;
            this.playerId = playerId;
        }
        public Profile() {
            this.name = "";
            this.remainigMoney = 0;
            this.highestProfit = 0;
            this.gameCount = 0;
            this.winCount = 0;
            this.playerId = 0;
        }

        public String getName() {
            return name;
        }

        public float getRemainigMoney() {
            return remainigMoney;
        }

        public float getHighestProfit() {
            return highestProfit;
        }

        public int getGameCount() {
            return gameCount;
        }

        public int getWinCount() {
            return winCount;
        }

        public int getPlayerId() {
            return playerId;
        }
    }


    static public Props props() {
        return Props.create(PlayerRegistryActor.class);
    }

    private final List<Player> users = new ArrayList<>();
    private DBQueries dbQueries = new DBQueries();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PlayerRegistryMessages.GetUser.class, getUser -> {

//                    getSender().tell(new Players(users), getSelf());
                    Profile a = dbQueries.GetPlayerProfile(getUser.getName());
//                    System.out.println("its working GetUser " + a.getName() + a.getPlayerId());

                    getSender().tell(new PlayerRegistryMessages.GetProfile(a.getName(),
                            a.getRemainigMoney(),
                            a.getHighestProfit(),
                            a.getGameCount(),
                            a.getWinCount(),
                            a.getPlayerId()), getSelf());
                }).match(PlayerRegistryMessages.CreateUser.class, createUser -> {
                    System.out.println("its working " + createUser.getUser().name);
                    boolean isUserExit = dbQueries.AddPlayer(createUser.getUser().name);
                    if (isUserExit) {
                        Profile a = dbQueries.GetPlayerProfile(createUser.getUser().name);
                        getSender().tell(new PlayerRegistryMessages.ActionPerformed(a), getSelf());
                    } else {
                        Profile a = dbQueries.GetPlayerProfile(createUser.getUser().name);
                        getSender().tell(new PlayerRegistryMessages.ActionPerformed(a), getSelf());
                    }
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
