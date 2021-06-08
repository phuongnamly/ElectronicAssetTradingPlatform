package server.database.schema;

import client.model.entity.Asset;
import client.model.entity.Inventory;
import client.model.mockInterface.AssetDataSource;

import java.sql.*;
import java.util.ArrayList;

/**
 * This is the JBDC class for asset table
 */
public class JBDCAssetDataSource implements AssetDataSource {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `asset` (\n" +
                    "  `asset_id` INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `asset_type` VARCHAR(100),\n" +
                    "  `asset_name` VARCHAR(100),\n" +
                    "  PRIMARY KEY (`asset_id`)\n" +
                    ");";


    private static final String CREATE_ASSET = "REPLACE INTO asset (asset_type, asset_name) VALUES (?,?);";

    private static final String EDIT_ASSET = "UPDATE asset SET asset_type = ?, asset_name = ? WHERE asset_id = ?";

    private static final String DELETE_ASSET = "DELETE FROM asset WHERE asset_id=?";

    private static final String GET_ASSET = "SELECT * FROM asset WHERE asset_id=?";

    private static final String GET_ALL_ASSETS = "SELECT * FROM asset";

    private static final String DELETE_ALL_ASSETS = "DELETE FROM asset";

    private static final String GET_LISTINGS = "SELECT listing.current_trade, listing.quantity, listing.price, listing.date, listing.inventory_id, inventory.inventory_id, inventory.asset_id, asset.asset_id FROM ((listing INNER JOIN inventory ON listing.inventory_id = inventory.inventory_id) INNER JOIN asset ON inventory.asset_id=asset.asset_id) where asset_id=? and listing.current_trade=? ";


    private Connection connection;

    private PreparedStatement create;

    private PreparedStatement edit;

    private PreparedStatement delete;

    private PreparedStatement get;

    private PreparedStatement getAll;

    private PreparedStatement deleteAll;

    private PreparedStatement getlistings;


    public JBDCAssetDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            create = connection.prepareStatement(CREATE_ASSET);
            edit = connection.prepareStatement(EDIT_ASSET);
            delete = connection.prepareStatement(DELETE_ASSET);
            get = connection.prepareStatement(GET_ASSET);
            getAll = connection.prepareStatement(GET_ALL_ASSETS);
            deleteAll = connection.prepareStatement(DELETE_ALL_ASSETS);
            getlistings = connection.prepareStatement(GET_LISTINGS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean create(Asset asset) {
        try {
            create.setString(1, asset.getAssetType());
            create.setString(2, asset.getAssetName());
            int rowsCount = create.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean edit(Asset asset) {
        try {
            edit.setString(1, asset.getAssetType());
            edit.setString(2, asset.getAssetName());
            edit.setInt(3, Integer.parseInt(asset.getAssetID()));
            int rowsCount = edit.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(String asset_id) {
        try {
            delete.setInt(1, Integer.parseInt(asset_id));
            int rowsCount = delete.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Asset> get(String asset_id) {
        ArrayList<Asset>  assets = new ArrayList<>();
        ResultSet rs = null;
        try {
            get.setInt(1, Integer.parseInt(asset_id));

            rs = get.executeQuery();
            if(rs.next()){
                Asset asset = new Asset();
                asset.setAssetID(rs.getString("asset_id"));
                asset.setAssetType(rs.getString("asset_type"));
                asset.setAssetName(rs.getString("asset_name"));
                assets.add(asset);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return assets;
    }

    public ArrayList<Asset> getAll() {
        ArrayList<Asset>  assets = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getAll.executeQuery();
            rs.next();
            while(rs.next()){
                Asset asset = new Asset();
                asset.setAssetID(rs.getString("asset_id"));
                asset.setAssetType(rs.getString("asset_type"));
                asset.setAssetName(rs.getString("asset_name"));
                assets.add(asset);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return assets;
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

    public ArrayList<Asset> getAllListingByAsset(Asset keyAsset) {
//        private static final String GET_LISTINGS = "SELECT listing.current_trade, listing.quantity, listing.price, listing.date, listing.inventory_id, inventory.inventory_id, inventory.asset_id, asset.asset_id FROM ((listing INNER JOIN inventory ON listing.inventory_id = inventory.inventory_id) INNER JOIN asset ON inventory.asset_id=asset.asset_id) where asset_id=? and listing.current_trade=? ";

        ArrayList<Asset> assets = new ArrayList<>();
        ResultSet rs = null;
        try {
            getlistings.setString(1, keyAsset.getAssetID());
            getlistings.setBoolean(2, keyAsset.getCurrentTrade());

            int index = 0;
            rs = getlistings.executeQuery();

            while(rs.next()){
                Asset asset = new Asset();
                asset.setQuantity(rs.getString("listing.quantity"));
                asset.setPrice(rs.getString("listing.price"));
                asset.setDate(rs.getString("listing.date"));
                assets.add(asset);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return assets;
    }
}
