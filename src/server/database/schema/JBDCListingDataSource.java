package server.database.schema;

import server.database.Entity.Listing;

import java.sql.*;
import java.util.ArrayList;

public class JBDCListingDataSource {

    public static final String CREATE_TABLE_LISTING =
            "CREATE TABLE IF NOT EXISTS `listing` (\n" +
                    "  `listing_id` INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `organisation_id` INTEGER,\n" +
                    "  `user_id` INTEGER,\n" +
                    "  `asset_id` INTEGER,\n" +
                    "  `current_trade` BOOLEAN,\n" +
                    "  `trade_type` ENUM('BUY','SELL'),\n" +
                    "  `quantity` INTEGER,\n" +
                    "  `price` INTEGER,\n" +
                    "  `date` DATETIME,\n" +
                    "  PRIMARY KEY (`listing_id`),\n" +
                    "  FOREIGN KEY (`organisation_id`) REFERENCES `organisation`(`organisation_id`),\n" +
                    "  FOREIGN KEY (user_id) REFERENCES user(user_id),\n" +
                    "  FOREIGN KEY (`asset_id`) REFERENCES `asset`(`asset_id`)\n" +
                    ");";


    private static final String CREATE_LISTING = "REPLACE INTO listing (organisation_id, user_id, asset_id, current_trade, trade_type, quantity, price, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String EDIT_LISTING = "UPDATE listing SET current_trade = ?, trade_type = ?, quantity = ?, price = ?, date = ? WHERE listing_id = ?";

    private static final String DELETE_LISTING = "DELETE FROM listing WHERE listing_id=?";

    private static final String GET_LISTING = "SELECT * FROM listing WHERE listing_id=?";

    private static final String GET_ALL_LISTINGS = "SELECT * FROM listing";

    private Connection connection;

    private PreparedStatement create;

    private PreparedStatement edit;

    private PreparedStatement delete;

    private PreparedStatement get;

    private PreparedStatement getAll;


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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean create(Listing listing) {
//        private static final String CREATE_LISTING = "REPLACE INTO listing (organisation_id, user_id, asset_id, current_trade, trade_type, quantity, price, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            create.setInt(1, Integer.parseInt(listing.getOrganisationID()));
            create.setInt(2, Integer.parseInt(listing.getUserID()));
            create.setInt(3, Integer.parseInt(listing.getAssetID()));
            create.setBoolean(4, listing.getCurrentTrade());
            create.setString(5, listing.getOrganisationID());
            create.setInt(6, Integer.parseInt(listing.getQuantity()));
            create.setInt(7, Integer.parseInt(listing.getPrice()));
            create.setString(8, listing.getDate());
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
                listing.setOrganisationID(rs.getString("organisation_id"));
                listing.setUserID(rs.getString("user_id"));
                listing.setAssetID(rs.getString("asset_id"));
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
                listing.setOrganisationID(rs.getString("organisation_id"));
                listing.setUserID(rs.getString("user_id"));
                listing.setAssetID(rs.getString("asset_id"));
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
}
