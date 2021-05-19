package server.database.JBDCDataSource;

import server.database.DBConnection;
import server.database.JBDCDataSource.Entity.Trade;
import server.database.JBDCDataSource.Entity.Enum.TradeType;

import java.sql.*;
import java.util.ArrayList;

public class JBDCTradeDataSource  {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `trade` (\n" +
                    "  `trade_id` INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `listing_id` INTEGER,\n" +
                    "  `user_id` INTEGER,\n" +
                    "  `trade_type` ENUM('BUY','SELL') NOT NULL,\n" +
                    "  `current_trade` BOOLEAN NOT NULL,\n" +
                    "  `date` DATETIME NOT NULL,\n" +
                    "  PRIMARY KEY (`trade_id`),\n" +
                    "  FOREIGN KEY (listing_id) REFERENCES listing(listing_id)),\n" +
                    "  FOREIGN KEY (user_id) REFERENCES user(user_id))\n" +
                    ");";

    private static final String CREATE_TRADE = "REPLACE INTO trade (listing_id, user_id, trade_type, current_trade, date) VALUES (?, ?, ?, ?, ?);";

    private static final String EDIT_TRADE = "UPDATE trade SET trade_type = ?, current_trade = ?, date = ? WHERE trade_id = ?";

    private static final String DELETE_TRADE = "DELETE FROM trade WHERE trade_id=?";

    private static final String GET_TRADE = "SELECT * FROM trade WHERE trade_id=?";

    private static final String GET_ALL_TRADES = "SELECT * FROM trade";

    private Connection connection;

    private PreparedStatement createTrade;

    private PreparedStatement editTrade;

    private PreparedStatement deleteTrade;

    private PreparedStatement getTrade;

    private PreparedStatement getAllTrades;


    public JBDCTradeDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            createTrade = connection.prepareStatement(CREATE_TRADE);
            editTrade = connection.prepareStatement(EDIT_TRADE);
            deleteTrade = connection.prepareStatement(DELETE_TRADE);
            getTrade = connection.prepareStatement(GET_TRADE);
            getAllTrades = connection.prepareStatement(GET_ALL_TRADES);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createTrade(Trade trade) {
        try {
            //     private static final String CREATE_TRADE = "REPLACE INTO trade (listing_id, user_id, trade_type, current_trade, date) VALUES (?, ?, ?, ?, ?);";
            createTrade.setInt(1, trade.getListingID());
            createTrade.setInt(2, trade.getUserID());
            createTrade.setString(3, trade.getTradeType().name());
            createTrade.setBoolean(4, trade.getCurrentTrade());
            createTrade.setTimestamp(5, Timestamp.valueOf(trade.getDate()));
            createTrade.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editTrade(int trade_id, Trade trade) {
        // EDIT_TRADE = "UPDATE trade SET trade_type = ?, current_trade = ?, date = ? WHERE trade_id = ?";
        try {
            createTrade.setString(1, trade.getTradeType().name());
            createTrade.setBoolean(2, trade.getCurrentTrade());
            createTrade.setTimestamp(3, Timestamp.valueOf(trade.getDate()));
            editTrade.setInt(4, trade_id);
            editTrade.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteTrade(int trade_id) {
        try {
            getTrade.setInt(1, trade_id);
            deleteTrade.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Trade getTrade(int trade_id) {
        Trade trade = new Trade();
        ResultSet rs = null;
        try {
            getTrade.setInt(1, trade_id);
            int index = 0;
            rs = getTrade.executeQuery();
            if(rs.next()){
                trade.setListingID(rs.getInt("listing_id"));
                trade.setUserID(rs.getInt("user_id"));
                trade.setTradeType(TradeType.valueOf(rs.getString("trade_type")));
                trade.setCurrentTrade(rs.getBoolean("current_type"));
                trade.setDate(rs.getDate("date"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return trade;
    }

    public ArrayList<Trade> getAllUsers() {
        ArrayList<Trade> trades = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getTrade.executeQuery();
            rs.next();
            while(rs.next()){
                Trade trade = new Trade();
                trade.setListingID(rs.getInt("listing_id"));
                trade.setUserID(rs.getInt("user_id"));
                trade.setTradeType(TradeType.valueOf(rs.getString("trade_type")));
                trade.setCurrentTrade(rs.getBoolean("current_type"));
                trade.setDate(rs.getDate("date"));
                trades.add(trade);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return trades;
    }
}
