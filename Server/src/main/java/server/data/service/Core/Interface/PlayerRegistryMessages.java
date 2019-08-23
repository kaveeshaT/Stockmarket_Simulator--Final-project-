package server.data.service.Core.Interface;

import server.data.service.Actors.PlayerRegistryActor.*;

import java.io.Serializable;

public interface PlayerRegistryMessages {

    class GetUsers implements Serializable {
    }

    class ActionPerformed implements Serializable {
        private final Profile player;

        public ActionPerformed(Profile player) {
            this.player = player;
        }

        public Profile getPlayer() {
            return player;
        }

    }
    class GetProfile implements Serializable {
        private final String name;
        private final float remainigMoney;
        private final float highestProfit;
        private final int gameCount;
        private final int winCount;
        private final int playerId;


        public GetProfile(String name, float remainigMoney, float highestProfit, int gameCount, int winCount, int playerId) {
            this.name = name;
            this.remainigMoney = remainigMoney;
            this.highestProfit = highestProfit;
            this.gameCount = gameCount;
            this.winCount = winCount;
            this.playerId = playerId;
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

    class CreateUser implements Serializable {
        private final Player player;

        public CreateUser(Player user) {
            this.player = user;
        }

        public Player getUser() {
            return player;
        }
    }

    class GetUser implements Serializable {
        private final String name;

        public GetUser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


}