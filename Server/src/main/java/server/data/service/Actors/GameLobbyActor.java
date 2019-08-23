package server.data.service.Actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import server.data.service.Core.Interface.GameLobbyMessage;
import server.data.service.Core.Interface.GameRoom;
import server.data.service.Core.Interface.Stock;
import server.data.service.Core.Services.DBQueries;

import java.util.ArrayList;
import java.util.List;


public class GameLobbyActor extends AbstractActor {
    private DBQueries dbQueries = new DBQueries();

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static class InitGameRoom {
        private int playerId;
        private String roomName;

        public InitGameRoom(int playerId, String roomName) {
            this.playerId = playerId;
            this.roomName = roomName;
        }

        public InitGameRoom() {
            this.playerId = 0;
            this.roomName = "";
        }


        public int getPlayerId() {
            return playerId;
        }

        public String getRoomName() {
            return roomName;
        }

    }

    public static class GameRooms {
        private final List<GameRoom> gameRooms;

        public GameRooms() {
            this.gameRooms = new ArrayList<>();
        }

        public GameRooms(List<GameRoom> gameRooms) {
            this.gameRooms = gameRooms;
        }

        public List<GameRoom> getGameRooms() {
            return gameRooms;
        }
    }

    public static class GameRoomPlayers {
        private final List<PlayerRegistryActor.Profile> gameRoomPlayers;

        public GameRoomPlayers() {
            this.gameRoomPlayers = new ArrayList<>();
        }

        public GameRoomPlayers(List<PlayerRegistryActor.Profile> gameRoomPlayers) {
            this.gameRoomPlayers = gameRoomPlayers;
        }

        public List<PlayerRegistryActor.Profile> getGameRoomPlayers() {
            return gameRoomPlayers;
        }
    }

    static public Props props() {
        return Props.create(GameLobbyActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(GameLobbyMessage.GetGameRoom.class, getGame -> {
                            dbQueries.AddGameRoom(getGame.getGameRoom().getRoomName(), getGame.getGameRoom().getPlayerId());
                            getSender().tell(new GameLobbyMessage.ActionPerformed(
                                    String.format("Game Room %s Created.", getGame.getGameRoom().getRoomName())), getSelf());
                        }
                ).match(GameLobbyMessage.GetAllGameRooms.class, getAllGame -> {
                    System.out.println("----------Start dbQueries-----------------------");
                    getSender().tell(new GameRooms(dbQueries.GetGameRooms()), getSelf());
                }).match(GameLobbyMessage.JoinGameRoom.class, joinGameRoom -> {
                    dbQueries.JoinGameRoom(joinGameRoom.getGameRoom().getRoomName(), joinGameRoom.getGameRoom().getPlayerId());
                    getSender().tell(new GameLobbyMessage.ActionPerformed(
                            String.format("Game Room %s Created.", joinGameRoom.getGameRoom().getRoomName())), getSelf());
                }).match(GameLobbyMessage.GetRoomPlayers.class, joinGameRoom -> {
                    getSender().tell(new GameRoomPlayers(dbQueries.GetRoomPlayersProfile(joinGameRoom.getRoomName())), getSelf());
                }).matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
