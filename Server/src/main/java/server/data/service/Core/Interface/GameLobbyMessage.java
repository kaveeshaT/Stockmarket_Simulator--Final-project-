package server.data.service.Core.Interface;

import server.data.service.Actors.GameLobbyActor.*;

import java.io.Serializable;

public interface GameLobbyMessage {


    class GetGameRoom implements Serializable {
        private final InitGameRoom initGameRoom;

        public GetGameRoom(InitGameRoom initGameRoom) {
            this.initGameRoom = initGameRoom;
        }

        public InitGameRoom getGameRoom() {
            return initGameRoom;
        }
    }
    class GetRoomPlayers implements Serializable {
        private final String roomName;

        public GetRoomPlayers(String roomName) {
            this.roomName = roomName;
        }

        public String getRoomName() {
            return roomName;
        }
    }
    class JoinGameRoom implements Serializable {
        private final InitGameRoom initGameRoom;

        public JoinGameRoom(InitGameRoom initGameRoom) {
            this.initGameRoom = initGameRoom;
        }

        public InitGameRoom getGameRoom() {
            return initGameRoom;
        }
    }
    class ActionPerformed implements Serializable {
        private final String description;

        public ActionPerformed(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

    }
    class GetAllGameRooms implements Serializable{

    }
}

