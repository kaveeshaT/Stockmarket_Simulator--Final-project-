package server.data.service.Core.Interface;

public class GameRoom {
    private String name;
    private int gameId;
    private int players[];

    public GameRoom() {
    }

    public GameRoom(String name, int gameId, int[] players) {
        this.name = name;
        this.gameId = gameId;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public int getGameId() {
        return gameId;
    }

    public int[] getPlayers() {
        return players;
    }
}
