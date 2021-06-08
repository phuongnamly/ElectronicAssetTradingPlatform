package server.database.schema;

import client.model.entity.Listing;
import client.model.mockInterface.ListingDataSource;

import java.sql.*;
import java.util.ArrayList;

public class JBDCListingDataSource implements ListingDataSource {

    public static final String CREATE_TABLE_LISTING =
            "CREATE TABLE IF NOT EXISTS `listing` (\n" +
                    "  `listing_id` INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `inventory_id` INTEGER,\n" +
                    "  `user_id` INTEGER,\n" +
                    "  `current_trade` BOOLEAN,\n" +
                    "  `trade_type` ENUM('BUY','SELL'),\n" +
                    "  `quantity` INTEGER,\n" +
                    "  `price` INTEGER,\n" +
                    "  `date` DATETIME,\n" +
                    "  PRIMARY KEY (`listing_id`),\n" +
                    "  FOREIGN KEY (`inventory_id`) REFERENCES `inventory`(`inventory_id`),\n" +
                    "  FOREIGN KEY (user_id) REFERENCES user(user_id),\n" +
                    ");";

    private static final String CREATE_LISTING = "REPLACE INTO listing (inventory_id, user_id, current_trade, trade_type, quantity, price, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String EDIT_LISTING = "UPDATE listing SET current_trade = ?, trade_type = ?, quantity = ?, price = ?, date = ? WHERE listing_id = ?";

    private static final String DELETE_LISTING = "DELETE FROM listing WHERE listing_id=?";

    private static final String GET_LISTING = "SELECT * FROM listing WHERE listing_id=?";

    private static final String GET_ALL_LISTINGS = "SELECT * FROM listing";

    private static final String DELETE_ALL_LISTINGS = "DELETE FROM listing";

    private static final String GET_ALL_LISTINGS_BASED_ON_CURRENT = "SELECT * FROM listing WHERE current_trade=?";

//    write many additional methods
//    private static final String GET_ALL_LISTINGS = "SELECT * FROM WHERE current_trade=?";

    private Connection connection;

    private PreparedStatement create;

    private PreparedStatement edit;

    private PreparedStatement delete;

    private PreparedStatement get;

    private PreparedStatement getAll;

    private PreparedStatement getAllCurrent;

    private PreparedStatement deleteAll;


    public JBDCListingDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE_LISTING);

            create = connection.prepareStatement(CREATE_LISTING);
            edit = connection.prepareStatement(EDIT_LISTING);
            delete = connection.prepareStatement(DELETE_LISTING);
            get = connection.prepareStatement(GET_LISTING);
            getAll = connection.prepareStatement(GET_ALL_LISTINGS);
            deleteAll = connection.prepareStatement(DELETE_ALL_LISTINGS);
            getAllCurrent = connection.prepareStatement(GET_ALL_LISTINGS_BASED_ON_CURRENT);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean create(Listing listing) {
//        private static final String CREATE_LISTING = "REPLACE INTO listing (inventory_id, user_id, current_trade, trade_type, quantity, price, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            create.setInt(1, Integer.parseInt(listing.getInventoryID()));
            create.setInt(2, Integer.parseInt(listing.getUserID()));
            create.setBoolean(3, listing.getCurrentTrade());
            create.setString(4, listing.getTradeType());
            create.setInt(5, Integer.parseInt(listing.getQuantity()));
            create.setInt(6, Integer.parseInt(listing.getPrice()));
            create.setString(7, listing.getDate());
            int rowsCount = create.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean edit(Listing listing) {
//        private static final String EDIT_LISTING = "UPDATE listing SET current_trade = ?, trade_type = ?, quantity = ?, price = ?, date = ? WHERE listing_id = ?";
        try {
            edit.setBoolean(1, listing.getCurrentTrade());
            edit.setString(2, listing.getTradeType());
            edit.setInt(3, Integer.parseInt(listing.getQuantity()));
            edit.setInt(1, Integer.parseInt(listing.getPrice()));
            edit.setInt(5,  Integer.parseInt(listing.getListingID()));
            int rowsCount = edit.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(String listing_id) {
        try {
            delete.setInt(1, Integer.parseInt(listing_id));
            int rowsCount = delete.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Listing> get(String listing_id) {
        ArrayList<Listing> listings = new ArrayList<>();
        ResultSet rs = null;
        try {
            get.setString(1, listing_id);
            int index = 0;
            rs = get.executeQuery();

            if(rs.next()){
                Listing listing = new Listing();
                listing.setListingID(rs.getString("listing_id"));
                listing.setInventoryID(rs.getString("inventory_id"));
                listing.setUserID(rs.getString("user_id"));
                listing.setCurrentTrade(rs.getBoolean("current_trade"));
                listing.setTradeType(rs.getString("trade_type"));
                listing.setQuantity(rs.getString("quantity"));
                listing.setPrice(rs.getString("price"));
                listing.setDate(rs.getString("date"));
                listings.add(listing);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listings;
    }

    public ArrayList<Listing> getAll() {
        ArrayList<Listing> listings = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getAll.executeQuery();
            int index = 0;

            while (rs.next()){
                Listing listing = new Listing();
                listing.setListingID(rs.getString("listing_id"));
                listing.setInventoryID(rs.getString("inventory_id"));
                listing.setUserID(rs.getString("user_id"));
                listing.setCurrentTrade(rs.getBoolean("current_trade"));
                listing.setTradeType(rs.getString("trade_type"));
                listing.setQuantity(rs.getString("quantity"));
                listing.setPrice(rs.getString("price"));
                listing.setDate(rs.getString("date"));
                listings.add(listing);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listings;
    }

    @Override
    public boolean deleteAll() {
        try {
            int rowsCount = deleteAll.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
