package server.data.service.Core.Services;

import server.data.service.Actors.BrokerActor;
import server.data.service.Actors.PlayerRegistryActor.*;
import server.data.service.Core.Interface.GameRoom;
import server.data.service.DBManger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBQueries {
    private DBManger st = new DBManger();

    public boolean AddPlayer(String playerName) {
        try {
            ResultSet rs = st.ExecuteQuery("select * from Players Where Name = '" + playerName + "'");
            if (!rs.next()) {
                st.ExecuteInsertQuery("insert into Players (Name,Remaing_Money,Game_Count) values ('" + playerName + "'," + 1000 + "," + 1 + ");");
                System.out.println("ResultSet in empty in Java");
            } else {
                System.out.println("User Already Exit");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Profile GetPlayerProfile(String username) {
        Profile profile = new Profile();
        System.out.println("ResultSet in empty in Java" + username);

        try {
            ResultSet rs = st.ExecuteQuery("select * from Players Where Name = '" + username + "'");
            if (!rs.next()) {
                System.out.println("ResultSet in empty in Java");
                return profile;
            } else {
                System.out.println("User Already Exit" + rs);
                profile = new Profile(rs.getString("Name"),
                        rs.getFloat("Remaing_Money"),
                        rs.getFloat("Highest_Profit"),
                        rs.getInt("Game_Count"),
                        rs.getInt("Win_Count"),
                        rs.getInt("PlayerId"));
                System.out.println("User Already Exit 12312312312" + rs.getInt("PlayerId"));
                System.out.println("User Already Exit sadasdad" + profile.getPlayerId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }
    public List<Profile> GetRoomPlayersProfile(String room) {
        Profile profile = new Profile();
        List<Profile> gameRoomList = new ArrayList<Profile>();

        System.out.println("ResultSet in empty in ava" + room);

        try {
            ResultSet rs = st.ExecuteQuery("select * from Players Where GameName = '" + room + "' LIMIT 0, 4 ");
            if (!rs.next()) {
                System.out.println("Result in empty in Java");
                return gameRoomList;
            } else {
                while (rs.next()) {
                    System.out.println("User Already Exit" + rs);
                    profile = new Profile(rs.getString("Name"),
                            rs.getFloat("Remaing_Money"),
                            rs.getFloat("Highest_Profit"),
                            rs.getInt("Game_Count"),
                            rs.getInt("Win_Count"),
                            rs.getInt("PlayerId"));
                    gameRoomList.add(profile);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameRoomList;
    }

    public void AddGameRoom(String roomName, int p1) throws SQLException {
        st.ExecuteInsertQuery("insert into GameRooms (Name,PlayerCount,Player1Id) values ('" + roomName + "'," + 1 + ","+ 1 + ");");
        st.ExecuteInsertQuery("update Players SET GameName = '" + roomName + "' Where PlayerId = " + p1 + ";");
    }
    public void JoinGameRoom(String roomName, int p1) throws SQLException {
        st.ExecuteInsertQuery("update Players SET GameName = '" + roomName + "' Where PlayerId = " + p1 + ";");
    }
    public Integer BuyStock(BrokerActor.StockBroker stock) throws SQLException {
        return st.ExecuteInsertQuery("insert into GameActivity (GameId,PlayerId,Activity,StockId,TotalPrice,StockAmount) values (" + stock.getRoomId() + "," + stock.getPlayerId() + ",'BUY'," + stock.getStockId() + "," + stock.getTotalPrice() + "," + stock.getStockAmount() + ");");

    }

    public Integer SellStock(BrokerActor.StockBroker stock) throws SQLException {
        return st.ExecuteInsertQuery("insert into GameActivity (GameId,PlayerId,Activity,StockId,TotalPrice,StockAmount) values (" + stock.getRoomId() + "," + stock.getPlayerId() + ",'SELL'," + stock.getStockId() + "," + stock.getTotalPrice() + "," + stock.getStockAmount() + ");");

    }

    public void UpdateBuyPlayerWallet(BrokerActor.StockBroker stock) throws SQLException {
        try {
            ResultSet rs = st.ExecuteQuery("select * from Players Where PlayerId = " + stock.getPlayerId());
            if (!rs.next()) {
                System.out.println("Player Dosent Exist");

            } else {
                System.out.println("User Already Exit" + rs);
                float playerProfit = rs.getFloat("Remaing_Money");
                playerProfit = playerProfit - stock.getTotalPrice();
                if (playerProfit < 0) {
                    playerProfit = 0;
                }
                st.ExecuteInsertQuery("update Players SET Remaing_Money =" + playerProfit + " Where PlayerId = " + stock.getPlayerId() + ";");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateSellPlayerWallet(BrokerActor.StockBroker stock) throws SQLException {
        try {
            ResultSet rs = st.ExecuteQuery("select * from Players Where PlayerId = " + stock.getPlayerId());
            if (!rs.next()) {
                System.out.println("Player Dosent Exist");

            } else {
                System.out.println("User Already Exit" + rs);
                float playerProfit = rs.getFloat("Remaing_Money");
                playerProfit = playerProfit + stock.getTotalPrice();

                st.ExecuteInsertQuery("update Players SET Remaing_Money =" + playerProfit + " Where PlayerId = " + stock.getPlayerId() + ";");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<GameRoom> GetGameRooms() {
        GameRoom gameRoom = new GameRoom();
        List<GameRoom> gameRoomList = new ArrayList<GameRoom>();
        try {
            ResultSet rs = st.ExecuteQuery("SELECT GameRooms.GameId,GameRooms.Name,JSON_ARRAYAGG(Players.PlayerId) AS PlayerId\n" +
                    "FROM GameRooms INNER JOIN Players ON GameRooms.Name = Players.GameName GROUP BY GameRooms.GameId ;");
            if (!rs.next()) {
                System.out.println("ResultSet in empty in Java");
                gameRoomList.add(gameRoom);
                return gameRoomList;
            } else {
                System.out.println("ResultSet");

                while (rs.next()) {
                    System.out.println("ResultsadasSet");
                    String[] r = rs.getString("PlayerId").replace("[", "").replace("]", "").split(",");
                    int[] array = new int[10];
                    for (int i = 0; i < r.length; i++) {
                        array[i] = Integer.parseInt(r[i].trim());
                        System.out.println(array[i]);
                    }
                    gameRoom = new GameRoom(rs.getString("Name"),
                            rs.getInt("GameId"),
                            array);

                    gameRoomList.add(gameRoom);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameRoomList;
    }


}
