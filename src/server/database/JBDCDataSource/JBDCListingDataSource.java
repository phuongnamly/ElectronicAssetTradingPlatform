package server.database.JBDCDataSource;

import server.database.DBConnection;
import server.database.JBDCDataSource.Entity.Enum.AccountType;
import server.database.JBDCDataSource.Entity.Listing;
import server.database.JBDCDataSource.Entity.User;

import java.sql.*;
import java.util.ArrayList;

public class JBDCListingDataSource {

    public static final String CREATE_TABLE_LISTING =
            "CREATE TABLE IF NOT EXISTS `listing` (\n" +
                    "  `listing_id` INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `organisation_id` INTEGER,\n" +
                    "  `asset_id` INTEGER,\n" +
                    "  `quantity` INT(MAXINT),\n" +
                    "  `price` INT(MAXINT),\n" +
                    "  PRIMARY KEY (`listing_id`)\n" +
                    "  FOREIGN KEY (listing_id) REFERENCES listing(listing_id)),\n" +
                    "  FOREIGN KEY (user_id) REFERENCES user(user_id))\n" +
                    ");";

    private static final String CREATE_LISTING = "REPLACE INTO listing (organisation_id, asset_id, quantity, price) VALUES (?, ?, ?, ?);";

    private static final String EDIT_LISTING = "UPDATE listing SET quantity = ?, price = ? WHERE listing_id = ?";

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

    public void create(Listing listing) {
        try {
            create.setInt(1, listing.getOrganisationID());
            create.setInt(2, listing.getAssetID());
            create.setInt(3, listing.getQuantity());
            create.setInt(4, listing.getPrice());
            create.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void edit(int id, Listing listing) {
        try {
            create.setInt(1, listing.getQuantity());
            create.setInt(2, listing.getPrice());
            edit.setInt(3, id);
            edit.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            get.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Listing get(String username) {
        Listing listing = new Listing();
        ResultSet rs = null;
        try {
            get.setString(1, username);
            int index = 0;
            rs = get.executeQuery();
            if(rs.next()){
                listing.setOrganisationID(rs.getInt("organisation_id"));
                listing.setAssetID(rs.getInt("asset_id"));
                listing.setQuantity(rs.getInt("quantity"));
                listing.setPrice(rs.getInt("price"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listing;
    }

    public ArrayList<Listing> getAll() {
        ArrayList<Listing>  listings = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = get.executeQuery();
            int index = 0;
            rs.next();
            while(rs.next()){
                Listing listing = new Listing();

                listing.setOrganisationID(rs.getInt("organisation_id"));
                listing.setAssetID(rs.getInt("asset_id"));
                listing.setQuantity(rs.getInt("quantity"));
                listing.setPrice(rs.getInt("price"));

                listings.add(listing);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listings;
    }
}
