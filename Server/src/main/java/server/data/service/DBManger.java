package server.data.service;

import java.sql.*;

public class DBManger {
    static Connection con = null;
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;

    public void getdbConnect() {
        dbConnect();
    }

    private void dbConnect() {

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String url = "jdbc:mysql://gamedatabase.cz626ljubhie.ap-south-1.rds.amazonaws.com:3306/";
            String userName = "pythonsa";
            String password = "123gamerds123";
            String dbName = "gameDb?useSSL=false";
            con = DriverManager.getConnection(url + dbName, userName, password);
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println("DB connection went wrong");
        }
    }//end dbConnect

    private void dbDisConnect() {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }//end dbDisConnect

    public void getdbDisConnect() {
        dbDisConnect();
        System.out.println("Disconnected!");

    }

    public ResultSet ExecuteQuery(String query) throws SQLException {
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public Integer ExecuteInsertQuery(String query) throws SQLException {
        int res = 0;
        try {
            stmt = con.createStatement();
            res = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return res;
    }
}

